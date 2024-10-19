package com.revshop.productservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sellerdetails")
public class SellerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;

    private String businessName;

    private String businessAddress;

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

    @Override
    public String toString() {
        return "SellerModel[sellerId = "  + sellerId + " , businessname : " + businessName + "]";

    }
}