package com.manas.rentalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findById(long productId);
}
