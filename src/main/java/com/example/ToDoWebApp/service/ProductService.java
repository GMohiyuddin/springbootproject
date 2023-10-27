package com.example.ToDoWebApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoWebApp.model.Product;
import com.example.ToDoWebApp.model.Seller;
import com.example.ToDoWebApp.repo.Productrepo;

import jakarta.servlet.http.Part;

@Service
public class ProductService {
	@Autowired
	Productrepo Prepo;

	public ProductService(Productrepo Prepo) {
		super();
		this.Prepo = Prepo;
	}
	
	public void uploadNewProduct(String pname,String Pcategory,String pdesc,String image,int pprice,Seller id) {
		Product p = new Product(0, pname, Pcategory, pdesc, image, pprice, id);
		Prepo.save(p);
	}
	public List<Product> getproductbysid(Seller id){
		return Prepo.findBySId(id);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
