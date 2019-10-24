package com.manas.rentalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manas.rentalapp.Dao.SignupDao;
import com.manas.rentalapp.service.SignupService;

@RequestMapping("/signup")
@RestController
public class SignupController {

	@Autowired
	private SignupService signupService;
	
	@RequestMapping(value="",method = RequestMethod.POST)
	public ResponseEntity<Boolean> signup(@RequestBody SignupDao signupDao){
		boolean result = signupService.signup(signupDao);
		if(result) {
			return new ResponseEntity<>(result,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}
}
