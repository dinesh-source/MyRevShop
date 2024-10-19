package com.example.client_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.Category;
import com.example.client_app.model.Product;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ProductController {
	
    @Autowired
    private RestTemplate restTemplate;
	
	@GetMapping("/api/v1/buyer-dashboard")
	public String viewDashboard() {	
		return "buyerDashboard";
	}
	
	@GetMapping("/api/v1/seller")
	public String showSeller() {
		return "sellerDashboard";
	}

	@GetMapping("/api/v1/products")
	public String viewProductsPage(Model model, HttpSession session) {
		List<Product> products = restTemplate.getForObject("http://localhost:8087/api/v1/products", List.class);

		List<Category> categories= restTemplate.getForObject("http://localhost:8087/api/v1/categories", List.class);
		
		model.addAttribute("categories",categories);
		
		Integer userId = (Integer) session.getAttribute("userId");
		List<Product> wishlistProducts = restTemplate.getForObject("http://localhost:8088/api/v1/wishlist?userId="+userId, List.class);
		model.addAttribute("products", products);
		model.addAttribute("wishlistProducts", wishlistProducts);
		return "view001";
	}
	
	@GetMapping("/api/v1/products/{id}")
    public String viewProductsById(@PathVariable("id") int productId, Model model) {
        Product products = restTemplate.getForObject("http://localhost:8087/api/v1/products/"+productId, Product.class);
        model.addAttribute("product", products);
//        double averageRating = productService.getRating(productId);
//        System.out.println(averageRating);
//        model.addAttribute("averageRating", averageRating);
//        model.addAttribute("selectedProduct", products);
        return "ProductDetails";
	}

	@GetMapping("/api/v1/seller/products")
    public String showProducts(HttpSession session, Model model) {
        int sellerid = (int) session.getAttribute("sellerid");
        System.out.println("product"+sellerid);
        List<Product> products = restTemplate.getForObject("http://localhost:8087/api/v1/seller/products?sellerid= "+sellerid, List.class);
        model.addAttribute("products", products);
        return "sellerProducts";
    }
	
}
