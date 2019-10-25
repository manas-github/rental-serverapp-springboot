package com.manas.rentalapp.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manas.rentalapp.security.model.JwtUser;
import com.manas.rentalapp.util.Security;

@Component
public class JwtValidator {


	@Autowired 
	private Security security;
	
    private String secret = "youtube";

    public JwtUser validate(String token) {
    	if(token.startsWith("Token")){
    		token = token.substring(6);
    	}
    	token = security.decrypt(token);
        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}