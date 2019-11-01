package com.manas.rentalapp.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TrendingProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Product product;
	
	@ElementCollection
    @CollectionTable(name="userDateMap")
    Map<UserProfile, LocalDateTime> userDateMap = new HashMap<>();
	private int hitCount;
	
	public int getHitCount() {
		return this.hitCount;
	}
	
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Map<UserProfile, LocalDateTime> getUserDateMap() {
		return userDateMap;
	}

	public void setUserDateMap(HashMap<UserProfile, LocalDateTime> userDateMap) {
		this.userDateMap = userDateMap;
	}
	
}
