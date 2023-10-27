package com.example.ToDoWebApp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ToDoWebApp.model.Product;
import com.example.ToDoWebApp.model.Seller;
import com.example.ToDoWebApp.repo.Productrepo;
import com.example.ToDoWebApp.service.ProductService;
import com.example.ToDoWebApp.service.SellerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;

@Controller
public class ProductController {
	@Autowired
	ProductService Pservice;
	@Autowired
	Productrepo Prepo;
	@Autowired
	SellerService service;

	private String extractfilename(Part file) {
		String cd = file.getHeader("content-disposition");
		System.out.println(cd);
		String[] items = cd.split(";");
		for (String string : items) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
	}

	@PostMapping("UploadProduct")
	public String uploadproduct(@Validated Product p, BindingResult result, @RequestParam Part image,
			HttpSession session, ModelMap model ) throws IOException {

		String savePath = "C:\\Users\\LENOVO\\Downloads\\ToDoWebApp\\ToDoWebApp\\src\\main\\resources\\META-INF\\resources\\images";
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		Part file1 = image;
		String filename = extractfilename(file1);
		file1.write(savePath + File.separator + filename);
		String savepath2 = "C:\\Users\\LENOVO\\Downloads\\ToDoWebApp\\ToDoWebApp\\src\\main\\resources\\META-INF\\resources\\images";
		File imageSaveDir = new File(savepath2);
		if (!imageSaveDir.exists()) {
			imageSaveDir.mkdir();
		}
		Pservice.uploadNewProduct(p.getPname(), p.getPcategory(), p.getPdesc(), p.getImage(), p.getPprice(), p.getId());
		System.out.println("product uploaded");
		return "seller-home";
	}
	@GetMapping("seller-manage-product")
	public String allproductpage(ModelMap model,@RequestParam List<Integer> id,HttpSession session) {
		List<Product> product = Pservice.getproductbysid((Seller) id);
		model.addAttribute("product", product);
		return "seller-manage-product";
	}

}
