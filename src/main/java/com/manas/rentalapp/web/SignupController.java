package com.manas.rentalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manas.Dao.SignupDao;
import com.manas.rentalapp.service.SignupService;

@RequestMapping("/api")
@RestController
public class SignupController {

	@Autowired
	private SignupService signupService;
	
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody SignupDao signupDao){
		boolean result = signupService.signup(signupDao);
		if(result) {
			return new ResponseEntity<>("Succesfull",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("User already exist",HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}
}
