package com.manas.rentalapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.SearchHistory;
import com.manas.rentalapp.model.UserProfile;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {

	List<SearchHistory> findByUserProfile(UserProfile userProfile);
	
	long deleteByUserProfileAndSearchKey(
		    @Param("UserProfile") UserProfile userProfile, 
		    @Param("SearchKey") String searchKey);

}
