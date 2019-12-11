package com.manas.rentalapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.TrendingProductRepository;

@Service
public class ScheduledService {

	@Autowired
	private TrendingProductRepository trendingRepository;

    @Scheduled(cron = "0 0 1 * * *")
	//@Scheduled(fixedRate = 1)
    @Transactional
	public void removeUnwantedDataFromTrendingProdcuts() {
		trendingRepository.findAll().forEach(trendingProduct->{
			Map<UserProfile, LocalDateTime> hm = trendingProduct.getUserDateMap();
			List<UserProfile> pastList = new ArrayList<>();
			hm.entrySet().stream().forEach(userDateEntry->{
				if(userDateEntry.getValue().compareTo(LocalDateTime.now().minusHours(24))<0) {
					pastList.add(userDateEntry.getKey());					
				}
			});
			pastList.forEach(user->{
				hm.remove(user);
			});
			trendingProduct.setHitCount(trendingProduct.getHitCount()-pastList.size());
			trendingRepository.save(trendingProduct);
		});
		trendingRepository.deleteByHitCount(0);
	}
	
}
