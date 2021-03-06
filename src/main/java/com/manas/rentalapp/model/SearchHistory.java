package com.manas.rentalapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SearchHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private UserProfile userProfile;
	
	
	private String searchKey;
	
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public String getSearchKey() {
		return this.searchKey;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}	
	
	
}