package com.manas.rentalapp.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manas.rentalapp.service.AccountVerificationService;

@RequestMapping("/accountVerification")
@RestController
public class AccountVerificationController{

	
	@Autowired
	private AccountVerificationService accountVerificationService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String activate(@RequestParam("secretVerificationId") String verificationId){
		if(verificationId==null) {
			return "Invalid link";
		}
		if(accountVerificationService.activateAccount(verificationId)) {
			return "Your account has been activated";
		}
		return "Link Expired";
	}

	
	
}
