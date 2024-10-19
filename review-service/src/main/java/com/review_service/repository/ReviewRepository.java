package com.review_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.review_service.entity.Review;



@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
}

