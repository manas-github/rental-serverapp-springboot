package com.manas.rentalapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.AccountVerification;

@Repository
public interface AccountVerificationRepository extends JpaRepository<AccountVerification,Long> {

	Optional<AccountVerification> findByVerificationId(String verificationId);
		
}
