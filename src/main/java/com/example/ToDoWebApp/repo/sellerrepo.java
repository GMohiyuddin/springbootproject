package com.example.ToDoWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDoWebApp.model.Seller;

public interface sellerrepo extends JpaRepository<Seller, Integer> {

	Seller findByEmailAndPassword(String email, String password);

}
