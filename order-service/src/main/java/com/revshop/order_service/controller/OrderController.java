package com.revshop.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.order_service.entity.Order;
import com.revshop.order_service.entity.OrderItem;
import com.revshop.order_service.entity.OrderResponse;
import com.revshop.order_service.entity.UserModel;
import com.revshop.order_service.respository.UserRepository;
import com.revshop.order_service.service.OrderService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/place")
	public ResponseEntity<?> createOrder(@RequestBody OrderResponse orderRes) {
		Order order = orderService.createOrder(orderRes.getUser(), orderRes.getCart(), orderRes.getShippingAddress(),
				orderRes.getBillingAddress());
		return ResponseEntity.ok(order);
	}

	@PostMapping("/items")
	public ResponseEntity<?> getOrders(@RequestBody Order order) {
		List<OrderItem> orderItems = orderService.getOrderItemsByOrder(order);
		return ResponseEntity.ok(orderItems);
	}

	@GetMapping("/orderHistory")
	public ResponseEntity<?> orderHistoryBySeller() {
		List<Order> orders = orderService.getOrdersForSeller();

		return ResponseEntity.ok(orders);
	}

	@GetMapping("/details")
	public ResponseEntity<?> orderDetails(@RequestParam("orderId") int orderId) {
		Order order = orderService.getOrderById(orderId);
		List<OrderItem> orderItems = orderService.getOrderItemsByOrder(order);
		return ResponseEntity.ok(orderItems);
	}

	@GetMapping("/history")
	public ResponseEntity<?> orderHistory(@RequestParam("userid") int userid, Model model) {
		UserModel user = userRepository.findByUserId(userid);
		if (user == null) {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}

		List<Order> orders = orderService.getOrdersByUser(user);
		return ResponseEntity.ok(orders);
	}
}