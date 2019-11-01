package com.manas.rentalapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.model.SearchHistory;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.SearchHistoryRepository;

@Service
public class SearchHistoryService {

	@Autowired
	private SearchHistoryRepository searchHistoryRepository;
	
	@Transactional
	public List<SearchHistory> getSearchHistory(UserProfile userProfile){
		List<SearchHistory> searchHistory = searchHistoryRepository.findByUserProfile(userProfile);
		return searchHistory;
	}
	
	@Transactional
	public Boolean addToSearchHistory(UserProfile userProfile, String searchKey){
		SearchHistory history = new SearchHistory();
		history.setUserProfile(userProfile);
		history.setSearchKey(searchKey);
		if(searchHistoryRepository.save(history)!=null) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public Boolean deleteFromSearchHistory(UserProfile userProfile, String searchKey){
		if(searchHistoryRepository.deleteByUserProfileAndSearchKey(userProfile, searchKey)>=0)
			return true;
		return false;
	}
	

}
