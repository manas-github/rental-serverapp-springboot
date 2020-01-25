package com.manas.rentalapp.web;

import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manas.rentalapp.Dao.OtpVerificationDao;
import com.manas.rentalapp.Dao.SignupDao;
import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.service.AccountVerificationService;
import com.manas.rentalapp.service.SignupService;
import com.manas.rentalapp.util.EmailService;
import com.manas.rentalapp.util.OtpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Operations pertaining to creating new account"})
@RequestMapping("/signup")
@RestController
public class SignupController {

	@Autowired
	private SignupService signupService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AccountVerificationService accountVerificationService;
	
	
	@ApiOperation(value = "create new user account")
	@RequestMapping(value="",method = RequestMethod.POST)
	public ResponseEntity<Boolean> signup(@RequestBody SignupDao signupDao){
		boolean result = signupService.signup(signupDao);
    	
		String accountVerificationString = accountVerificationService.getVerificationString(new UserDao(signupDao.getEmail()));
		if(accountVerificationString!="error") {
			String accountVerificationLink = "https://manas-rental-serverapp.herokuapp.com/accountVerification?secretVerificationId="+accountVerificationString;
			try {
				emailService.sendMail(signupDao.getEmail(), "Rental app registration","Please click on the following link to complete your registration : "+accountVerificationLink );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(result) {
			return new ResponseEntity<>(result,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(result,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}
	
	@RequestMapping(value="/sendOtp", method = RequestMethod.POST)
	public ResponseEntity<Boolean> sendOtp(@RequestBody OtpVerificationDao otpVerificationDao){
		boolean result = signupService.sendOtp(otpVerificationDao);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value="/verifyOtp", method = RequestMethod.POST)
	public ResponseEntity<Boolean> verifyOtp(@RequestBody OtpVerificationDao otpVerificationDao){
		boolean result = signupService.verifyOtp(otpVerificationDao);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
