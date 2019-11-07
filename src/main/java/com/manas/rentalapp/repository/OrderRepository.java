package com.manas.rentalapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.Cart;
import com.manas.rentalapp.model.Order;
import com.manas.rentalapp.model.UserProfile;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findAllByUserId(long l);
	
}
