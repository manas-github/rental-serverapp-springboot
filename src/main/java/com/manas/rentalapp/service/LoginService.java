package com.manas.rentalapp.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.LoginDao;
import com.manas.rentalapp.model.Login;
import com.manas.rentalapp.repository.LoginRepository;
import com.manas.rentalapp.security.JwtGenerator;
import com.manas.rentalapp.security.model.JwtUser;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private JwtUser  jwtUser;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Transactional
	public String login(LoginDao loginDao) {
		Login user = loginRepository.findByEmail(loginDao.getEmail());
		if(user ==null) {
			return null;
		}
		else {	
			if(user.getPassword().equals(loginDao.getPassword())) {
				user.setIpAddress(loginDao.getIpAddress());
				user.setLastLogin(new Date());
				loginRepository.save(user);
				jwtUser.setUserName(user.getEmail());
				jwtUser.setRole("user");
				jwtUser.setId(user.getUserProfile().getId());
				String token = jwtGenerator.generate(jwtUser);
				return token;
			}
			else {
				return null;
			}
		}
		
	}
}
