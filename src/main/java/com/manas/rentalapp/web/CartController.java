package com.manas.rentalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manas.rentalapp.Dao.AddProductToCartDao;
import com.manas.rentalapp.Dao.LoginDao;
import com.manas.rentalapp.model.Cart;
import com.manas.rentalapp.service.CartService;
import com.manas.rentalapp.service.LoginService;

@RequestMapping("/api")
@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/cart/add",method = RequestMethod.POST)
	public ResponseEntity<String> updateCart(@RequestBody AddProductToCartDao product){
		boolean result = cartService.addProductToCart(product);
		if(result) {
			return new ResponseEntity<>("Succesfull",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Unable to update cart",HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}
}
