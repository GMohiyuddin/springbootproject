package com.example.ToDoWebApp.controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ToDoWebApp.model.Seller;
import com.example.ToDoWebApp.repo.sellerrepo;
import com.example.ToDoWebApp.service.SellerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SellerController {

	@Autowired
	SellerService service;

	@Autowired
	sellerrepo repo;

	@GetMapping("/")
	public String indexpage() {
		return "index";
	}

	@GetMapping("Seller-registration")
	public String sellerregistpage() {
		return "Seller-registration";
	}

	@PostMapping("/register")
	public String registerseller(ModelMap model, @Validated Seller s, BindingResult result) {
		service.registerseller(s.getName(), s.getAddress(), s.getEmail(), s.getContact(), s.getPassword());
		System.out.println("registerSuccessful");
		return "seller-login";
	}

	@GetMapping("seller-login")
	public String sellerlogin() {
		return "seller-login";
	}

	@PostMapping("/loginseller")
	public String loginseller(ModelMap model, @RequestParam String email, @RequestParam String password,
			HttpSession session) {

		Seller s = service.loginseller(email, password);
		if (s != null) {
			session.setAttribute("seller", s);
			model.addAttribute("seller", s);
			return "seller-home";
		} else {
			model.addAttribute("error", "invalid email and password");
			return "seller-login";
		}

	}

	@GetMapping("/seller-home")
	public String sellerhome(ModelMap model, HttpSession session, @RequestParam int id, @Valid Seller s) {
		Optional<Seller> s1 = service.getsellerbyid(id);
		model.addAttribute("seller", s1);
		return "seller-home";
	}

	@GetMapping("seller-profile")
	public String sellerprofile(@Valid Seller s, HttpSession session) {
		return "seller-profile";
	}

	@GetMapping("seller-logout")
	public String sellerlogout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "seller-login";
	}

	@PostMapping("/updateseller")
	public String updateseller(ModelMap model, HttpSession session, @Valid Seller s, @RequestParam int id,
			@RequestParam String name, @RequestParam String email, @RequestParam String address,
			@RequestParam long contact) {
		Optional<Seller> s1 = service.getsellerbyid(id);
		s.setName(name);
		s.setEmail(email);
		s.setAddress(address);
		s.setContact(contact);
		service.updateuser(s);
		model.addAttribute("seller", s);
		session.setAttribute("seller", s);
		return "redirect:seller-profile";
	}

	@GetMapping("seller-change-password")
	public String changepasswordpage(@Valid Seller s, HttpSession session, ModelMap model, @RequestParam int id) {
		Optional<Seller> s1 = service.getsellerbyid(id);
		model.addAttribute("seller", s1);
		return "seller-change-password";
	}

	@PostMapping("/updatepassword")
	public String updatepassword(@RequestParam String op, @RequestParam int id, @RequestParam String np,
			@RequestParam String cnp, ModelMap model, HttpSession session, @Valid Seller s) {
		Optional<Seller> s1 = service.getsellerbyid(id);
		Seller s11 = (Seller) session.getAttribute("seller");
		if (op.equals(s11.getPassword())) {
			if (np.equals(cnp)) {
				s11.setPassword(np);
				service.updateuser(s11);
				System.out.println("password updated");
				return "seller-home";
			} else {
				model.addAttribute("error", "new passwords are incorrect");
			}
		} else {
			model.addAttribute("error", "incorrect old password");
		}
		return "seller-change-password";
	}

	@GetMapping("seller-forget-password")
	public String forgetpasswordpage() {
		return "seller-forget-password";
	}

	@PostMapping("otp_varification_page")
	public String getemail(@RequestParam String email, ModelMap model) {
		Seller s1 = service.emailexist(email);
		if (s1 != null) {
			Random r = new Random();
			int otp1 = r.nextInt(999999);
			model.addAttribute("opt1", otp1);
			System.out.println(otp1);
			model.addAttribute("email", email);
			return "seller-verify-otp";
		} else {
			model.addAttribute("error1", "Email do not exist");
			return "seller-forget-password";
		}
	}

	@PostMapping("getverify")
	public String getverify(@RequestParam String otp2, @RequestParam String otp1, @RequestParam String email,
			ModelMap model) {
		if (otp2.equals(otp1)) {
			model.addAttribute("email", email);
			return "seller-new-password";
		} else {
			model.addAttribute("error", "otp did not matched");
			System.out.println(otp1);
			System.out.println(otp2);
			return "seller-verify-otp";

		}
	}

	@PostMapping("newpassword")
	public String sellernewpassword(ModelMap model, @RequestParam String email, @RequestParam String np,
			@RequestParam String cnp) {
		Seller s = service.emailexist(email);
		if (np.equals(cnp)) {
			s.setPassword(np);
			service.updateuser(s);
			return "seller-login";
		} else {
			model.addAttribute("msg", "Both passwords are different");
			return "seller-new-password";
		}
	}
	
	@GetMapping("seller-upload-product")
	public String uploadproductpage(@Valid Seller s, HttpSession session, ModelMap model, @RequestParam int id) {
		Optional<Seller> s1 = service.getsellerbyid(id);
		model.addAttribute("seller", s1);
		return "seller-upload-product";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
