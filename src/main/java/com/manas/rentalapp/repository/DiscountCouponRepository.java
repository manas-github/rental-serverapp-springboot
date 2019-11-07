package com.manas.rentalapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.DiscountCoupon;

@Repository
public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long> {
		
}
