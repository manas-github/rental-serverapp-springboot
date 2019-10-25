package com.manas.rentalapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manas.rentalapp.security.EncryptDecrypt;


@Component
public class Security {

	@Autowired
	private EncryptDecrypt encryptDecrypt;
	
	private static final String SECRET_KEY = "DoYouKnow";
	
	public String encrypt(String data) {
		return encryptDecrypt.encrypt(data, SECRET_KEY);
	}
	
	public String decrypt(String data) {
		return encryptDecrypt.decrypt(data, SECRET_KEY);
	}
	
	
}
