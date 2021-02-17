package com.manas.rentalapp.util;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

@Component
public class OtpService {

	private static Random rnd = new Random();

	public String getRandomNumber(int digCount) {
	    StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++)
	        sb.append((char)('0' + rnd.nextInt(10)));
	    return sb.toString();
	}
	

    public boolean sendOtp(String recepient, String message) {
    	HttpResponse response = Unirest.post("https://www.fast2sms.com/dev/bulk")
    			  .header("authorization", "FhLS3qKj4jFejhMyj8MXIeNjYgXovjBK1HRVdtTc0kGxHRU")
    			  .header("Content-Type", "application/x-www-form-urlencoded")
    			  .body("sender_id=FSTSMS&message="+message+"&language=english&route=p&numbers="+recepient)
    			  .asString();
        return true;
    }
}
