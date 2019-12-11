package com.manas.rentalapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.model.Product;
import com.manas.rentalapp.model.TrendingProduct;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.TrendingProductRepository;

@Service
public class TrendingProductService {
		
	@Autowired
	private TrendingProductRepository trendingRepository;
	
	@Autowired ScheduledService scheduledService;
	
	public List<Product> getTrendingProducts( int count){

		//scheduledService.removeUnwantedDataFromTrendingProdcuts();
		List<Product> productList = new ArrayList<Product>();
		List<Integer> correspondingCount = new ArrayList<Integer>();
		System.out.println(count);
		trendingRepository.findAll().forEach(trendingProduct->{
			System.out.println(productList.size());
			if(productList.size()<count) {
				productList.add(trendingProduct.getProduct());
				correspondingCount.add(trendingProduct.getHitCount());
			}
			else {
				int minIndex = correspondingCount.indexOf(Collections.min(correspondingCount));
				if(correspondingCount.get(minIndex)<trendingProduct.getHitCount()) {
					productList.remove(minIndex);
					correspondingCount.remove(minIndex);
					productList.add(trendingProduct.getProduct());
					correspondingCount.add(trendingProduct.getHitCount());
				}
			}
		});
		
		return productList;
	}
	
	public void addToTrendingProduct(UserProfile user, Product product) {
		TrendingProduct existingTrendingProduct = trendingRepository.findByProduct(product);
		if(existingTrendingProduct==null) {
			TrendingProduct newTrendingProduct = new TrendingProduct();
			newTrendingProduct.setHitCount(1);
			newTrendingProduct.setProduct(product);
			HashMap<UserProfile,LocalDateTime> userDateMap = new HashMap<>();
			userDateMap.put(user,LocalDateTime.now());
			newTrendingProduct.setUserDateMap(userDateMap);
			trendingRepository.save(newTrendingProduct);
		}
		else {
			Map<UserProfile, LocalDateTime> userDateMap = existingTrendingProduct.getUserDateMap();
			if(userDateMap.containsKey(user)) {
				userDateMap.put(user, LocalDateTime.now());
			}
			else {
				userDateMap.put(user, LocalDateTime.now());
				existingTrendingProduct.setHitCount(existingTrendingProduct.getHitCount()+1);
			}
			trendingRepository.save(existingTrendingProduct);

		}
	}
	
}
