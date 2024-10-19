package com.example.client_app.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Product {

	private int productId;

	private Category category;

//	private Size size;

	private String name;

	private Gender gender = Gender.Male;

	private String description;

	private String imageUrl;

	private double price;

	private Double discountPrice;

	private int quantityAvailable;

	private int thresholdQuantity = 0;

	private Timestamp createdAt;

	protected void onCreate() {
		createdAt = Timestamp.valueOf(LocalDateTime.now());
	}

	// Getters and Setters
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	public Size getSize() {
//		return size;
//	}
//
//	public void setSize(Size size) {
//		this.size = size;
//	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getThresholdQuantity() {
		return thresholdQuantity;
	}

	public void setThresholdQuantity(int thresholdQuantity) {
		this.thresholdQuantity = thresholdQuantity;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
