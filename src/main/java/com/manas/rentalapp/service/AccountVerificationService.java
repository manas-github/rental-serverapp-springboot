package com.manas.rentalapp.service;



import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.model.AccountStatus;
import com.manas.rentalapp.model.AccountVerification;
import com.manas.rentalapp.model.Cart;
import com.manas.rentalapp.model.Login;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.AccountVerificationRepository;
import com.manas.rentalapp.repository.LoginRepository;
import com.manas.rentalapp.repository.UserProfileRepository;

@Service
public class AccountVerificationService {

	@Autowired
	private AccountVerificationRepository accountVerificationRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired LoginRepository loginRepository;
	
	@Transactional
	public String getVerificationString(UserDao user) {
		String verificationId = getAlphaNumericString(240);
		accountVerificationRepository.findByVerificationId(verificationId);
		Optional<AccountVerification> accountVerification = accountVerificationRepository.findByVerificationId(verificationId);
		if(!accountVerification.isPresent()) {
			AccountVerification newAccountVerification = new AccountVerification();
			UserProfile userProfile = userProfileRepository.findByEmail(user.getEmail());
			newAccountVerification.setUserProfile(userProfile);
			newAccountVerification.setVerificationId(verificationId);
			accountVerificationRepository.save(newAccountVerification);
			return verificationId;
		} else {
			return "error";
		}
	}
	
	@Transactional
	public Boolean activateAccount(String verificationId) {
		Optional<AccountVerification> accountVerification = accountVerificationRepository.findByVerificationId(verificationId);
		if(!accountVerification.isPresent()) {
			return false;
		} else {
			Login user = loginRepository.findByEmail(accountVerification.get().getUserProfile().getEmail());
			user.setAccountStatus(AccountStatus.ACTIVE);
			loginRepository.save(user);
			accountVerificationRepository.deleteById(accountVerification.get().getId());
			return true;

		}
	}
	
	static String getAlphaNumericString(int n) 
    { 
          String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(n); 
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
              sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
        return sb.toString(); 
    } 

}

