package com.example.client_app.dto;

import java.util.List;

import com.example.client_app.model.Cart;
import com.example.client_app.model.UserModel;

public class OrderResponse {
	
	private List<Cart> cart;
	private UserModel user;
	private String billingAddress;
	private String shippingAddress;

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
