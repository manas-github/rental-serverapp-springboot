package com.manas.rentalapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.model.SearchHistory;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.security.JwtValidator;
import com.manas.rentalapp.service.SearchHistoryService;
import com.manas.rentalapp.service.UserService;

@RestController
@RequestMapping("/api/v1/productSearchHistory")
public class SearchHistoryController {

	
	@Autowired
	private JwtValidator jwtValidator;
	
	@Autowired
	private SearchHistoryService searchHistoryService;
	
	@Autowired UserService userService;
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ResponseEntity<String> addToSearchHistory(@RequestHeader("authorization") String token, @RequestBody String searchKey){
		String email = jwtValidator.validate(token).getUserName();
		UserProfile userProfile = userService.getUserDetails(new UserDao(email));
		boolean result = searchHistoryService.addToSearchHistory(userProfile, searchKey);
		if(result)
			return new ResponseEntity<>("Succesfull",HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public List<SearchHistory> getSearchHistory(@RequestHeader("authorization") String token){
		String email = jwtValidator.validate(token).getUserName();
		UserProfile userProfile = userService.getUserDetails(new UserDao(email));
		return searchHistoryService.getSearchHistory(userProfile);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity<String> remove(@RequestHeader("authorization") String token, @RequestBody String searchKey){
		String email = jwtValidator.validate(token).getUserName();
		UserProfile userProfile = userService.getUserDetails(new UserDao(email));
		boolean result = searchHistoryService.deleteFromSearchHistory(userProfile, searchKey.substring(0,searchKey.length()-1));
		if(result)
			return new ResponseEntity<>("Succesfull",HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);	
	}
}
