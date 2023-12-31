package com.example.ToDoWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ToDoWebApp.model.Seller;

public interface sellerrepo extends JpaRepository<Seller, Integer> {

	Seller findByEmailAndPassword(String email, String password);

	@Query("SELECT s FROM Seller s WHERE s.email = ?1")
	Seller findbyemail(String email);
}
