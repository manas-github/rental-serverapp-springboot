package com.manas.rentalapp.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.Dao.SignupDao;
import com.manas.rentalapp.model.AccountStatus;
import com.manas.rentalapp.model.Login;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.LoginRepository;
import com.manas.rentalapp.repository.UserProfileRepository;

@Service
public class SignupService {
	
	@Autowired 
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Transactional
	public boolean signup(SignupDao signupDao) {
		
		UserProfile userProfileInstance = userProfileRepository.findByEmail(signupDao.getEmail());
		Login loginInstance = loginRepository.findByEmail(signupDao.getEmail());
		
		if(userProfileInstance!=null || loginInstance!=null) {
			return false;
		}
		else {
			UserProfile userProfile =  new UserProfile();
			userProfile.setFirstName(signupDao.getFirstName());
			userProfile.setLastName(signupDao.getLastName());
			userProfile.setMobile(signupDao.getMobile());
			userProfile.setEmail(signupDao.getEmail());
			userProfile.setLastName(signupDao.getLastName());
			userProfile.setUpdatedOn(new Date());
			userProfile.setCreatedOn(new Date());
			userProfileRepository.save(userProfile);

			UserProfile userProfileInstance1 = userProfileRepository.findByEmail(signupDao.getEmail());
			if(userProfileInstance1!=null) {
				Login login = new Login();
				login.setEmail(signupDao.getEmail());
				login.setPassword(signupDao.getPassword());
				login.setIpAddress(signupDao.getIpAddress());
				login.setUserProfile(userProfileInstance1);
				login.setAccountStatus(AccountStatus.ACTIVE);
				login.setLastLogin(new Date());
				loginRepository.save(login);
				return true;
			}
		}
		return false;
	}
}
