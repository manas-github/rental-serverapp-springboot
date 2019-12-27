package com.manas.rentalapp.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.OtpVerificationDao;
import com.manas.rentalapp.Dao.SignupDao;
import com.manas.rentalapp.model.AccountStatus;
import com.manas.rentalapp.model.Login;
import com.manas.rentalapp.model.Otp;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.LoginRepository;
import com.manas.rentalapp.repository.OtpRepository;
import com.manas.rentalapp.repository.UserProfileRepository;
import com.manas.rentalapp.util.OtpService;

@Service
public class SignupService {
	
	@Autowired 
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired 
	private OtpRepository otpRepository;
	
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
				login.setAccountStatus(AccountStatus.INACTIVE);
				login.setLastLogin(new Date());
				loginRepository.save(login);
				return true;
			}
		}
		return false;
	}
	
	@Transactional
	public boolean sendOtp(OtpVerificationDao otpVerificationDao) {
		String mobile  = otpVerificationDao.getMobile();
		if(mobile.length()!=10)
			return false;
		String otp = otpService.getRandomNumber(5);
		Otp newOtp;
		Optional<Otp> otpExisting = otpRepository.findByMobile(mobile);
		if(otpExisting.isPresent()) {
			newOtp = otpExisting.get();
		} else {
			newOtp = new Otp();
			newOtp.setMobile(mobile);
			newOtp.setOtp(otp);
		}
		String message="Your OTP for RENTIGO registration is "+newOtp.getOtp();
		if(otpService.sendOtp(mobile, message)) {
			otpRepository.save(newOtp);
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean verifyOtp(OtpVerificationDao otpVerificationDao) {
		Optional<Otp> otpExisting = otpRepository.findByMobile(otpVerificationDao.getMobile());
		if(!otpExisting.isPresent()) {
			return false;
		} else {
			if(otpExisting.get().getOtp().equals(otpVerificationDao.getOtp())) {
				otpRepository.deleteById(otpExisting.get().getId());
				return true;
			}
			else {
				return false;
			}
		}

	}
}
