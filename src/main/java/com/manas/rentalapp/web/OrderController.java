package com.manas.rentalapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manas.rentalapp.Dao.OrderDao;
import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.model.Order;
import com.manas.rentalapp.security.JwtValidator;
import com.manas.rentalapp.service.OrderService;

@RequestMapping("/api/v1/order")
@RestController
public class OrderController {

	@Autowired
	private JwtValidator jwtValidator;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create",method = RequestMethod.POST)
	public ResponseEntity<String> createOrder(@RequestHeader("authorization") String token, @RequestBody OrderDao orderDao){
		String email = jwtValidator.validate(token).getUserName();
		String orderId = orderService.createOrder(new UserDao(email), orderDao);
		if(orderId!=null) {
			return new ResponseEntity<>(orderId,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getAllOrder(@RequestHeader("authorization") String token){
		String email = jwtValidator.validate(token).getUserName();
		List<Order> orders = orderService.getAllOrder(new UserDao(email));
		if(orders!=null) {
			return new ResponseEntity<>(orders,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}
	}
}

