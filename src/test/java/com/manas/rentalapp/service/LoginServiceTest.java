package com.manas.rentalapp.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.manas.rentalapp.Dao.LoginDao;
import com.manas.rentalapp.Dao.SignupDao;
import com.manas.rentalapp.model.AccountStatus;
import com.manas.rentalapp.model.Login;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.LoginRepository;
import com.manas.rentalapp.repository.UserProfileRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
 public class LoginServiceTest {

	
	private LoginRepository loginRepository;
		
	@Autowired
	private SignupService signupService;
	
	@Autowired
	private UserProfileRepository user;
	
	@Test
	public void login() {
		SignupDao signupDao = new SignupDao();
		signupDao.setEmail("manas.rishav@gmail.com");
		signupDao.setPassword("12345Aa@");
		signupService.signup(signupDao);
		LoginDao loginDao = new LoginDao();
		loginDao.setEmail("manas.rishav@gmail.com");
		loginDao.setPassword("12345Aa@");
		Login user = loginRepository.findByEmail(loginDao.getEmail());
		if(user ==null) {
			assertEquals(0, 1);
		}
		else {	
			assertEquals(1, 1);

		}
		
	}
}

