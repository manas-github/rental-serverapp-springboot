package com.manas.rentalapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manas.rentalapp.Dao.ProductDao;
import com.manas.rentalapp.model.Product;
import com.manas.rentalapp.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
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
	
}
