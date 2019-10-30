package com.manas.rentalapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.manas.rentalapp.model.Product;
import com.manas.rentalapp.model.TrendingProduct;

@Repository
public interface TrendingProductRepository extends JpaRepository<TrendingProduct, Integer> {

	TrendingProduct findByProduct(Product product);

	int deleteByHitCount(int i);

}
