package com.manas.rentalapp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp,Long> {

	Optional<Otp> findByMobile(String mobile);
		
}
