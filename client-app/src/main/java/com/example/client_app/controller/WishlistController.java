package com.example.client_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.Product;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1/wishlist")
public class WishlistController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
    public String showWishlist(HttpSession session, Model model) {
    	Integer userId = (Integer) session.getAttribute("userId");
    	List<Product> products = restTemplate.getForObject("http://localhost:8088/api/v1/wishlist?userId="+userId, List.class);
    	model.addAttribute("products", products);
    	return "wishlist";
    }
	
	@PostMapping
	public String addToWishlist(Model model, HttpSession session, @RequestParam("productId") Integer productId) {
		Integer userId = (Integer) session.getAttribute("userId");
		Integer status = restTemplate.getForObject("http://localhost:8088/api/v1/wishlist/add?userId="+userId+"&productId="+productId, Integer.class);
		if(status == 200) {
			return "redirect:/api/v1/products";
		}
		else {
			return "notfound";
		}
	}
	
	@DeleteMapping
	public String removeProduct(HttpSession session, @RequestParam("productId") Integer productId) {
		Integer userId = (Integer) session.getAttribute("userId");
		Integer status = restTemplate.getForObject("http://localhost:8088/api/v1/wishlist/delete?userId="+userId+"&productId="+productId, Integer.class);
		if(status == 200) {
			return "redirect:/api/v1/products";
		}
		else {
			return "notfound";
		}
	}
}
