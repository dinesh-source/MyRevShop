package com.example.client_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Size {

    private int sizeId;

    private String sizeName;

    @JsonIgnore
    private Category category;

    // Getters and Setters
    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    // Getters and setters

	@Override
	public String toString() {
		return "Size [sizeId=" + sizeId + ", sizeName=" + sizeName + ",]";
	}
}

