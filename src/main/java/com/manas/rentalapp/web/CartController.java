package com.manas.rentalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.manas.rentalapp.Dao.CartDao;
import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.dto.CartDto;
import com.manas.rentalapp.service.CartService;

@RequestMapping("/api/v1/cart")
@RestController
public class CartController{

	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public CartDto getCart(@RequestBody UserDao userDao){
		CartDto cart = cartService.getCart(userDao);
		return cart;
	}
	
	@RequestMapping(value="/getCartItemCount", method = RequestMethod.POST)
	public int getCartItemCount(@RequestBody UserDao userDao) {
		return cartService.getCartCount(userDao);
	}
	
	@RequestMapping(value="/addProduct",method = RequestMethod.PATCH)
	public ResponseEntity<String> updateCart(@RequestBody CartDao cartDao){
		boolean result = cartService.addProductToCart(cartDao);
		if(result) {
			return new ResponseEntity<>("Succesfull",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Unable to update cart",HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	@RequestMapping(value="/increaseQuantity",method = RequestMethod.PATCH)
	public ResponseEntity<Boolean> increaseQuantity(@RequestBody CartDao cartDao){
		boolean result = cartService.addProductToCart(cartDao);
		if(result) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	@RequestMapping(value="/removeProduct",method = RequestMethod.PATCH)
	public ResponseEntity<Boolean> removeItem(@RequestBody CartDao cartDao){
		boolean result = cartService.removeProductFromCart(cartDao);
		if(result) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	@RequestMapping(value="/decreaseQuantity",method = RequestMethod.PATCH)
	public ResponseEntity<Boolean> decreaseQuantity(@RequestBody CartDao cartDao){
		boolean result = cartService.decreaseQuantity(cartDao);
		if(result) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	@RequestMapping(value="/clear",method = RequestMethod.DELETE)
	public ResponseEntity<String> decreaseQuantity(@RequestBody UserDao userDao){
		boolean result = cartService.clearCart(userDao);
		if(result) {
			return new ResponseEntity<>("Succesfull",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Unable to update cart",HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	
}
