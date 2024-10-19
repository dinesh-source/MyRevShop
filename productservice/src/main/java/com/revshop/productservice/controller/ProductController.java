package com.revshop.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.productservice.entity.Category;
import com.revshop.productservice.entity.Product;
import com.revshop.productservice.service.ProductService;

@RestController
@RequestMapping
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//	@Autowired
//	private WishlistService wishlistService;
		
//	@GetMapping("/api/v1/buyer-dashboard")
//	public String viewDashboard() {
//		return "buyerDashboard";
//	}
//	@GetMapping("/api/v1/seller/products")
//	public ResponseEntity<?> showProducts(@RequestParam("sellerId") int sellerId) {
//		List<Product> products = productService.getProductsBySeller(sellerId);
//		return ResponseEntity.ok(products);
//	}

	@GetMapping("/api/v1/products")
	public ResponseEntity<?> viewProductsPage() {
		List<Product> products = productService.getAllProducts();
		
//		Integer userId = (Integer) session.getAttribute("userId");
//		List<Product> wishlistProducts = wishlistService.getWishlist(userId);

//		model.addAttribute("wishlistProducts", wishlistProducts);
		return ResponseEntity.ok(products);
	}
	@GetMapping("/api/v1/categories")
	public ResponseEntity<?> viewProductsCats() {

		List<Category> categories= productService.getAllCategories();
		
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/api/v1/products/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") Integer productId) {
		Product product = productService.findById(productId);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/api/v1/seller/products")
    public ResponseEntity<?> showProducts(@RequestParam("sellerid") int sellerid) {
        System.out.println("prodservice"+sellerid);
        List<Product> products = productService.getProductsBySeller(sellerid);
        return ResponseEntity.ok(products);
    }
}
