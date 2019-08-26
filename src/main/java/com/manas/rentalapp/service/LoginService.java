package com.manas.rentalapp.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.LoginDao;
import com.manas.rentalapp.model.Login;
import com.manas.rentalapp.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Transactional
	public boolean login(LoginDao loginDao) {
		Login user = loginRepository.findByEmail(loginDao.getEmail());
		if(user ==null) {
			return false;
		}
		else {	
			if(user.getPassword().equals(loginDao.getPassword())) {
				user.setIpAddress(loginDao.getIpAddress());
				user.setLastLogin(new Date());
				loginRepository.save(user);
				return true;
			}
			else {
				return false;
			}
		}
		
	}
}
