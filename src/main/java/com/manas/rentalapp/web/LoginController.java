package com.manas.rentalapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manas.rentalapp.Dao.LoginDao;
import com.manas.rentalapp.service.LoginService;

@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="",method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginDao loginDao){
		String token = loginService.login(loginDao);
		if(token!=null) {
			return new ResponseEntity<>(token,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(token,HttpStatus.UNAUTHORIZED);
		}
		
		
	}
}

