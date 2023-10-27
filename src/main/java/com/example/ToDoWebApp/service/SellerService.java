package com.example.ToDoWebApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoWebApp.model.Seller;
import com.example.ToDoWebApp.repo.sellerrepo;

@Service
public class SellerService {
	@Autowired
	sellerrepo repo;

	public SellerService(sellerrepo repo) {
		super();
		this.repo = repo;
	}

	public void registerseller(String name, String address, String email, long contact, String password) {
		Seller s = new Seller(0, name, address, email, contact, password);
		repo.save(s);
	}

	public Seller loginseller(String email, String password) {
		return repo.findByEmailAndPassword(email, password);
	}
	public Optional<Seller> getsellerbyid(int id) {
		return repo.findById(id);
	}
	public void updateuser(Seller s) {
		repo.save(s);
	}
	public Seller emailexist(String email) {
		return repo.findbyemail(email);
	}
}
