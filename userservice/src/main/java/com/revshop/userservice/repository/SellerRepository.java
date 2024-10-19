package com.revshop.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.userservice.entity.SellerModel;


@Repository
public interface SellerRepository extends JpaRepository<SellerModel, Integer> {
	SellerModel findByUsermodelUserId(Integer userId);
	
}