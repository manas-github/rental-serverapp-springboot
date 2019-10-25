package com.manas.rentalapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manas.rentalapp.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {

	Optional<Cart> findByUserProfileId(long l);
	
}
