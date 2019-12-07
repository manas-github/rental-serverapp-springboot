package com.manas.rentalapp.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.UserProfileRepository;

@Service
public class UserService {
	
	@Autowired 
	private UserProfileRepository userProfileRepository;
	
	@Transactional
	public UserProfile getUserDetails(UserDao userDao) {
		
		UserProfile userProfile = userProfileRepository.findByEmail(userDao.getEmail());
		return userProfile;
	
	}

	@Transactional
	public UserProfile updateUserProfile(UserDao userDao, UserProfile updatedUserProfile) {
		UserProfile existingUserProfile = userProfileRepository.findByEmail(userDao.getEmail());
		existingUserProfile.setFirstName(updatedUserProfile.getFirstName());
		existingUserProfile.setLastName(updatedUserProfile.getLastName());
		existingUserProfile.setMobile(updatedUserProfile.getMobile());
		return userProfileRepository.save(existingUserProfile);
		
	}
	@Transactional
	public UserProfile updateAddress(UserDao userDao, String address) {
		UserProfile existingUserProfile = userProfileRepository.findByEmail(userDao.getEmail());
		existingUserProfile.setAddress(address);
		return userProfileRepository.save(existingUserProfile);
		
	}
}
