package com.example.client_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.Product;
import com.example.client_app.model.Review;

@Controller
@RequestMapping("/api/v1/reviews")
public class ReviewController {
	
	@Autowired
	private RestTemplate restTemplate;
		
	@GetMapping("/{id}")
	public String displayReviewForm(Model model, @PathVariable int id) {
		model.addAttribute("review", new Review());
		model.addAttribute("productId", id);
		return "showReview";
	}
	
	@PostMapping("/{id}")
	public String addReview(@PathVariable int id, @ModelAttribute Review review) {
		Product product = restTemplate.getForObject("http://localhost:8087/api/v1/products/"+id, Product.class);
		if(product != null) {
			review.setProduct(product);
			Integer status = restTemplate.postForObject("http://localhost:8049/api/v1/reviews/add", review, Integer.class);
			if(status == 200) {				
				return "redirect:/api/v1/orders/history";			
			}
		}
		return "notfound";
	}
}
