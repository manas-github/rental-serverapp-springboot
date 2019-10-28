package com.manas.rentalapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manas.rentalapp.Dao.ProductDao;
import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.dto.CartDto;
import com.manas.rentalapp.model.Product;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.security.JwtValidator;
import com.manas.rentalapp.service.ProductService;
import com.manas.rentalapp.service.SearchHistoryService;
import com.manas.rentalapp.service.UserService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SearchHistoryService searchHistoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtValidator jwtValidator;
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ResponseEntity<String> addProduct(@RequestBody ProductDao productDao){
		
		boolean result = productService.addProduct(productDao);
		if(result)
			return new ResponseEntity<>("Succesfull",HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public List<Product> getActiveProducts(){
		List<Product> products = productService.getAllActiveProducts();
		return products;
		
	}
	
	@RequestMapping(value="/furnitures",method = RequestMethod.GET)
	public List<Product> getActiveFurnitures(){
		List<Product> products = productService.getAllActiveFurnitures();
		return products;
	}
	
	@RequestMapping(value="/appliances",method = RequestMethod.GET)
	public List<Product> getActiveAppliance(){
		List<Product> products = productService.getAllActiveAppliances();
		return products;
	}
	
	@RequestMapping(value="/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable long productId) {
		Product product =  productService.getProductById(productId);
		if(product!=null)
			return new ResponseEntity<>(product,HttpStatus.OK);
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="search/{searchKey}",method = RequestMethod.GET)
	public List<Product> getProductBySearching(@RequestHeader("authorization") String token,@PathVariable String searchKey){
		String email = jwtValidator.validate(token).getUserName();
		UserProfile userProfile = userService.getUserDetails(new UserDao(email));
		searchHistoryService.addToSearchHistory(userProfile, searchKey);
		List<Product> products = productService.getProductBySearchingTitle(searchKey);
		return products;
	}
	
	@RequestMapping(value="keyPressSearch/{searchKey}",method = RequestMethod.GET)
	public List<String> getProductByHotSearchingTitle(@PathVariable String searchKey){
		List<String> titles = productService.getProductTitleByHotSearch(searchKey);
		return titles;
	}
	
}
