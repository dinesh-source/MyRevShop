package com.revshop.order_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revshop.order_service.entity.Cart;
import com.revshop.order_service.entity.Order;
import com.revshop.order_service.entity.OrderItem;
import com.revshop.order_service.entity.UserModel;
import com.revshop.order_service.repository.OrderItemRepository;
import com.revshop.order_service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order createOrder(UserModel user, List<Cart> cartItems, String shippingAddress, String billingAddress) {
        Order order = createOrderEntity(user, cartItems, shippingAddress, billingAddress);
        order = orderRepository.save(order);
        
        List<OrderItem> orderItems = createOrderItems(order, cartItems);
        orderItemRepository.saveAll(orderItems);

        return order;
    }

    private Order createOrderEntity(UserModel user, List<Cart> cartItems, String shippingAddress, String billingAddress) {
        Order order = new Order();
        order.setUserModel(user);
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(Order.OrderStatus.Pending);

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);

        return order;
    }

    private List<OrderItem> createOrderItems(Order order, List<Cart> cartItems) {
        return cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPriceAtPurchase(cartItem.getProduct().getPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByUser(UserModel user) {
        return orderRepository.findByUserModelOrderByOrderDateDesc(user);
    }

    public List<OrderItem> getOrderItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    public List<Order> getOrdersForSeller() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public void updateOrderStatus(Order order, Order.OrderStatus status) {
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
}
