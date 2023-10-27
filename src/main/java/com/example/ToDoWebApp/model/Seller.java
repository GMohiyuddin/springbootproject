package com.example.ToDoWebApp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name, address, email,password;
	private long contact;
	
	@OneToMany
	private List<Product> products;

	public Seller(int id, String name, String address, String email, long contact, String password) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.password = password;
	}
	public Seller() {
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", password="
				+ password + ", contact=" + contact + ", products=" + products + "]";
	}
}
