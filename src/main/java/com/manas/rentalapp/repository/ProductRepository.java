package com.manas.rentalapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findById(long productId);
	
   // @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    @Query("FROM Product p where p.title LIKE '%?1%'")
	List<Product> findBySearchingTitle(String searchKey);
}
