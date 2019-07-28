package com.manas.rentalapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

	UserProfile findByEmail(String email);
	
	
}
