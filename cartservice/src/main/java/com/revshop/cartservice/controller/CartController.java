package com.revshop.cartservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.cartservice.entity.*;
import com.revshop.cartservice.service.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/get")
	public ResponseEntity<List<Cart>> showCartPage(@RequestBody UserModel userModel) {
		List<Cart> cartItems = cartService.getCartItemsByuserModel(userModel);
		return ResponseEntity.ok(cartItems);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addToCart(@RequestBody Cart cart) {
		cartService.addToCart(cart);
		return ResponseEntity.ok(200);
	}

	@GetMapping("/delete")
	public ResponseEntity<?> deleteFromCart(@RequestParam("productId") Integer productId, @RequestParam("userId") Integer userId) {
		Cart cart = cartService.findByUserAndProduct(userId, productId);
		cartService.deleteFromCart(cart);
		return ResponseEntity.ok(200);
	}

	@GetMapping("/update")
	public ResponseEntity<?> updateQuantity(@RequestParam("productId") Integer productId, @RequestParam("userId") Integer userId,
			@RequestParam("quantity") Integer quantity) {
		Cart cart = cartService.findByUserAndProduct(userId, productId);
		System.out.println("Quantity" + quantity);
		if (quantity > 0) {
			cart.setQuantity(quantity);
			cartService.updateCart(cart);
		} else {
			cartService.deleteFromCart(cart);
		}
		return ResponseEntity.ok(200);
	}
}
