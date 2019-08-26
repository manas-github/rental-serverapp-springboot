package com.manas.rentalapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manas.rentalapp.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

	Login findByEmail(String email);
		
}
