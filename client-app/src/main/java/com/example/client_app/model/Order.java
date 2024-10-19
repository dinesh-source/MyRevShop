package com.example.client_app.model;

import java.time.LocalDateTime;

public class Order {
	
	private Integer orderId;	
	private UserModel userModel;	
	private double totalAmount;	
	private String shippingAddress;	
	private String billingAddress;	
	private OrderStatus orderStatus = OrderStatus.Pending;	
	private LocalDateTime orderDate;
	
	public enum OrderStatus {
		Pending, Shipped, Delivered, Cancelled
	}
	 public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public UserModel getUserModel() {
			return userModel;
		}

		public void setUserModel(UserModel userModel) {
			this.userModel = userModel;
		}

		public Double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}

		public String getBillingAddress() {
			return billingAddress;
		}

		public void setBillingAddress(String billingAddress) {
			this.billingAddress = billingAddress;
		}

		public OrderStatus getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(OrderStatus orderStatus) {
			this.orderStatus = orderStatus;
		}

		public LocalDateTime getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
		}

}
