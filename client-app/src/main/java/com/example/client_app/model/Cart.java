package com.example.client_app.model;

public class Cart {
	
	private int cartId;
    private Product product;
    private UserModel userModel;
    private int quantity;
    
    public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserModel getUser() {
        return userModel;
    }

    public void setUser(UserModel user) {
        this.userModel = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
