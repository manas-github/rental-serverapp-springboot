package com.manas.rentalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.manas.rentalapp.Dao.CartDao;
import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.dto.CartDto;
import com.manas.rentalapp.security.JwtValidator;
import com.manas.rentalapp.service.CartService;
import com.manas.rentalapp.util.Security;

@RequestMapping("/api/v1/cart")
@RestController
public class CartController{

	@Autowired
	private CartService cartService;
	
	@Autowired JwtValidator jwtValidator;
	
	@Autowired Security security;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public CartDto getCart(@RequestHeader("authorization") String token){
		String email = jwtValidator.validate(token).getUserName();
		CartDto cart = cartService.getCart(new UserDao(email));
		return cart;
	}
	
	@RequestMapping(value="/getCartItemCount", method = RequestMethod.POST)
	public int getCartItemCount(@RequestHeader("authorization") String token) {
		String email = jwtValidator.validate(token).getUserName();
		return cartService.getCartCount(new UserDao(email));
	}
	
	@RequestMapping(value="/addProduct",method = RequestMethod.PATCH)
	public ResponseEntity<String> updateCart(@RequestHeader("authorization") String token,@RequestBody CartDao cartDao){
		String email = jwtValidator.validate(token).getUserName();
		boolean result = cartService.addProductToCart(new UserDao(email),cartDao);
		if(result) {
			return new ResponseEntity<>("Succesfull",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Unable to update cart",HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	@RequestMapping(value="/increaseQuantity",method = RequestMethod.PATCH)
	public ResponseEntity<Boolean> increaseQuantity(@RequestHeader("authorization") String token,@RequestBody CartDao cartDao){
		String email = jwtValidator.validate(token).getUserName();
		boolean result = cartService.addProductToCart(new UserDao(email),cartDao);
		if(result) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	@RequestMapping(value="/removeProduct",method = RequestMethod.PATCH)
	public ResponseEntity<Boolean> removeItem(@RequestHeader("authorization") String token,@RequestBody CartDao cartDao){
		String email = jwtValidator.validate(token).getUserName();
		boolean result = cartService.removeProductFromCart(new UserDao(email),cartDao);
		if(result) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.METHOD_NOT_ALLOWED);
		}	
	}
	
	@RequestMapping(value="/decreaseQuantity",method = RequestMethod.PATCH)
	public ResponseEntity<Boolean> decreaseQuantity(@RequestHeader("authorization") String token,@RequestBody CartDao cartDao){
		String email = jwtValidator.validate(token).getUserName();
		boolean result = cartService.decreaseQuantity(new UserDao(email),cartDao);
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
