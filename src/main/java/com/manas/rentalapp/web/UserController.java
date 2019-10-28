package com.manas.rentalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.security.JwtValidator;
import com.manas.rentalapp.service.UserService;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtValidator jwtValidator;
	
	@RequestMapping(value="",method = RequestMethod.POST)
	public ResponseEntity<UserProfile> getUser(@RequestHeader("authorization") String token){
		String email = jwtValidator.validate(token).getUserName();
		UserProfile user = userService.getUserDetails(new UserDao(email));
		if(!user.getEmail().isEmpty()) {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ResponseEntity<UserProfile> update(@RequestHeader("authorization") String token, @RequestBody UserProfile userProfile){
		System.out.print(userProfile.toString());
		String email = jwtValidator.validate(token).getUserName();
		UserProfile user = userService.updateUserProfile(new UserDao(email),userProfile);
		if(!user.getEmail().isEmpty()) {
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
