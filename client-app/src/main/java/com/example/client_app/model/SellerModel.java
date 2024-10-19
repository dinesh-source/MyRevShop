package com.example.client_app.model;

public class SellerModel {

    private Integer sellerId;

    private String businessName;

    private String businessAddress;

    private UserModel usermodel;

    public SellerModel() {
    }

    public SellerModel(String businessName, String businessAddress) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public UserModel getUsermodel() {
        return usermodel;
    }

    public void setUsermodel(UserModel usermodel) {
        this.usermodel = usermodel;
    }
 
    @Override    
    public String toString() {
		return "SellerModel[sellerId = "  + sellerId + " , businessname : " + businessName + "]";
    	
    }
}
