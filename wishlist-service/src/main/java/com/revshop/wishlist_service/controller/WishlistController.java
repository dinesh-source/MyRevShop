package com.revshop.wishlist_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.wishlist_service.entity.Product;
import com.revshop.wishlist_service.service.WishlistService;

@RestController
@RequestMapping("/api/v1/wishlist")
public class WishlistController {
	
	 @Autowired
	 private WishlistService wishlistService;
	 
	 @GetMapping
	 public ResponseEntity<?> getProducts(@RequestParam("userId") int userId){
		 List<Product> products = wishlistService.getWishlist(userId);
		 return ResponseEntity.ok(products);
	 } 
	 
	 @GetMapping("/add")
	 public ResponseEntity<?> addToWishlist(@RequestParam Integer userId, @RequestParam Integer productId){
	     wishlistService.addToWishlist(userId, productId);
	     return ResponseEntity.ok(200);
	 }
	 
	 @GetMapping("/delete")
	 public ResponseEntity<?> removeProduct(@RequestParam Integer userId, @RequestParam Integer productId){
		 wishlistService.removeFromWishlist(userId, productId);
		 return ResponseEntity.ok(200);
	 }
}
