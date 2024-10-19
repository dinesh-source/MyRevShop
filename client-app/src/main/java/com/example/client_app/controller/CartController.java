package com.example.client_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.Cart;
import com.example.client_app.model.Product;
import com.example.client_app.model.UserModel;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1/cart")
public class CartController {

	private final static String USER_URL = "http://localhost:8090/api/v1/user?userId=";
	private final static String CART_URL = "http://localhost:8093/api/v1/cart";

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
    public String showCartPage(Model model, HttpSession session) {
    	int userId = (Integer) session.getAttribute("userId");
    	UserModel userModel = restTemplate.getForObject(USER_URL+userId, UserModel.class);
    	System.out.println(userModel + "User Model");
    	if(userModel != null) {
    		List<Cart> cartItems = restTemplate.postForObject(CART_URL+"/get", userModel, List.class);
    		model.addAttribute("cartItems", cartItems);
    		return "showCart";
    	}
    	else {
    		return "notfound";
    	}	
    }
	
	@PostMapping
	public String addToCart(Model model, @ModelAttribute Cart cart, @RequestParam("productId") int productId,
			@RequestParam("quantity") int quantity, HttpSession session) {
		System.out.println("Quantity" + quantity);
		int userId = (Integer) session.getAttribute("userId");
		UserModel userModel = restTemplate.getForObject(USER_URL+userId, UserModel.class);
		Product product = restTemplate.getForObject("http://localhost:8087/api/v1/products/"+productId, Product.class);
		cart.setUser(userModel);
		cart.setQuantity(quantity);
		cart.setProduct(product);
		Integer status = restTemplate.postForObject(CART_URL+"/add", cart, Integer.class);
		if(status == 200) {
			return "redirect:/api/v1/cart";
		}else {
			return "notfound";
		}
	}
	
	@PostMapping("/delete")
	public String deleteProduct(HttpSession session, @RequestParam("productId") Integer productId) {
		int userId = (Integer) session.getAttribute("userId");
		Integer status = restTemplate.getForObject(CART_URL+"/delete?productId="+productId+"&userId="+userId, Integer.class);
		if(status == 200) {
			return "redirect:/api/v1/cart";
		}else {
			return "notfound";
		}
	}
	
	@PostMapping("/update")
	public String updateQuantity(HttpSession session, @RequestParam("productId") Integer productId,
			@RequestParam("quantity") Integer quantity) {
		System.out.println("Quantity" + quantity);
		int userId = (Integer) session.getAttribute("userId");
		Integer status = restTemplate.getForObject(CART_URL+"/update?productId="+productId+"&userId="+userId+"&quantity="+quantity, Integer.class);
		if(status == 200) {
			return "redirect:/api/v1/cart";
		}else {
			return "notfound";
		}
	}
}
