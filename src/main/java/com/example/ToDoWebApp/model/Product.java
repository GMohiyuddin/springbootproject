package com.example.ToDoWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int pid;
	private String pname, pcategory, pdesc,image;
	private int pprice;
	@ManyToOne
	private Seller id;




	public Product(int pid, String pname, String pcategory, String pdesc, String image, int pprice, Seller id) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pcategory = pcategory;
		this.pdesc = pdesc;
		this.image = image;
		this.pprice = pprice;
		this.id = id;
	}



	public Product() {
		super();
	}

	

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcategory() {
		return pcategory;
	}

	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public Seller getId() {
		return id;
	}

	public void setId(Seller id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", image=" + image + ", pname=" + pname + ", pcategory=" + pcategory + ", pdesc="
				+ pdesc + ", pprice=" + pprice + ", id=" + id + "]";
	}

}
