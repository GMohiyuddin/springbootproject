package com.example.ToDoWebApp.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ToDoWebApp.model.Product;
import com.example.ToDoWebApp.model.Seller;

public interface Productrepo extends JpaRepository<Product,Integer> {

	@Query("SELECT p FROM Product p WHERE p.id = :id")
    List<Product> findBySId(@Param("id") Seller id);
	
	
	
}