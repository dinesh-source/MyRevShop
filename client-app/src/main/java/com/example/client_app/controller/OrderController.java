package com.example.client_app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.dto.OrderResponse;
import com.example.client_app.model.Cart;
import com.example.client_app.model.Order;
import com.example.client_app.model.OrderItem;
import com.example.client_app.model.Product;
import com.example.client_app.model.UserModel;

//import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {

	private final static String USER_URL = "http://localhost:8090/api/v1/user?userId=";
	private final static String CART_URL = "http://localhost:8093/api/v1/cart";

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping
	public String checkOut(Model model, HttpSession session) {
		int userId = (Integer) session.getAttribute("userId");
		UserModel userModel = restTemplate.getForObject(USER_URL + userId, UserModel.class);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<UserModel> requestEntity = new HttpEntity<>(userModel, headers);

		ResponseEntity<List<Cart>> responseEntity = restTemplate.exchange("http://localhost:8093/api/v1/cart/get",
				HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Cart>>() {
				});

		List<Cart> cartItems = responseEntity.getBody();

		Map<Integer, Integer> productQuantityMap = new HashMap<>();
		double total = 0;

		for (Cart item : cartItems) {
			int productId = item.getProduct().getProductId();
			int quantity = item.getQuantity();
			double price = item.getProduct().getDiscountPrice();

			productQuantityMap.put(productId, productQuantityMap.getOrDefault(productId, 0) + quantity);
			total += quantity * price;
		}

		model.addAttribute("wallet", userModel.getWalletBalance());
		model.addAttribute("total", total);
		model.addAttribute("cartItems", cartItems);
		return "checkOut";
	}

	@GetMapping("/place")
	public String placeOrder(@RequestParam String shippingAddress, @RequestParam String billingAddress,
			HttpSession session, Model model) {

		int userId = (Integer) session.getAttribute("userId");
		UserModel user = restTemplate.getForObject(USER_URL + userId, UserModel.class);
		if (user == null) {
			return "redirect:/api/v1/login";
		}

		List<Cart> cartItems = restTemplate.postForObject(CART_URL + "/get", user, List.class);
		if (cartItems.isEmpty()) {
			model.addAttribute("error", "Your cart is empty");
			return "checkOut";
		}
		OrderResponse orderRes = new OrderResponse();
		orderRes.setCart(cartItems);
		orderRes.setUser(user);
		orderRes.setBillingAddress(billingAddress);
		orderRes.setShippingAddress(shippingAddress);

		Order order = restTemplate.postForObject("http://localhost:8099/api/v1/orders/place", orderRes, Order.class);
//	        Order order = orderService.createOrder(user, cartItems, shippingAddress, billingAddress);
//	        cartService.clearCart(user);
//	      try {
//				emailService.sendOTPMessage(user.getUserEmail(), "Placed an order", "Your order has been placed");
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}

//	        double orderAmount = order.getTotalAmount();
//	        boolean paymentSuccess = walletService.debit(user, orderAmount);
//	        if (paymentSuccess) {
//	            orderService.updateOrderStatus(order, Order.OrderStatus.Shipped);
//	        } else {
//	            model.addAttribute("error", "Payment failed. Please check your wallet balance.");
//	            return "checkOut";
//	        }
		List<OrderItem> orderItems = restTemplate.postForObject("http://localhost:8099/api/v1/orders/items", order,
				List.class);
		model.addAttribute("orderItems", orderItems);
		model.addAttribute("order", order);
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("billingAddress", billingAddress);
		return "orderConfirmation";
	}

	@GetMapping("/orderHistory")
	public String orderHistoryBySeller(HttpSession session, Model model) {
		int sellerid = (Integer) session.getAttribute("sellerid");
		List<Order> orders = restTemplate.getForObject("http://localhost:8099/api/v1/orders/orderHistory", List.class);
		model.addAttribute("orders", orders);
		return "orderHistoryForSeller";
	}

	@GetMapping("/history")
	public String orderHistory(HttpSession session, Model model) {
		int userid = (Integer) session.getAttribute("userId");
		if (userid == 0) {
			return "redirect:/api/v1/login";
		}
		List<Order> orders = restTemplate.getForObject("http://localhost:8099/api/v1/orders/history?userid=" + userid,
				List.class);
		model.addAttribute("orders", orders);
		return "orderHistory";
	}

	@GetMapping("/details")
	public String orderDetails(@RequestParam("orderId") int orderId, Model model) {
//        Order order = restTemplate.getForObject("http://localhost:8099/api/v1/orders/details?orderId="+orderId,Order.class);
		List<OrderItem> orderItems = restTemplate
				.getForObject("http://localhost:8099/api/v1/orders/details?orderId=" + orderId, List.class);
		model.addAttribute("orderItems", orderItems);
		return "orderDetails";
	}
}
