package com.revshop.wishlist_service.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.revshop.wishlist_service.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne
    @JoinColumn(name = "size_id",nullable = false)
	private Size size;

	@Column(name = "name", nullable = false, length = 150)
	private String name;

	@Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "enum('Male','Female','Other')", nullable = false)
	private Gender gender = Gender.Male;

	@Column(name = "description")
	private String description;

	@Column(name = "image_url", length = 255)
	private String imageUrl;

	@Column(name = "price", nullable = false)
    private double price;
    
    @Column(name = "discount_price")
    private Double discountPrice;
    
    @Column(name = "quantity_available", nullable = false)
    private int quantityAvailable;
    
    @Column(name = "threshold_quantity", nullable = false)
    private int thresholdQuantity = 0;
    
    @Column(name = "created_at", nullable = false, updatable = false)
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

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

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
