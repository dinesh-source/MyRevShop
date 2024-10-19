package com.revshop.productservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.productservice.entity.SellerModel;

@Repository
public interface SellerRepository extends JpaRepository<SellerModel, Integer> {

}