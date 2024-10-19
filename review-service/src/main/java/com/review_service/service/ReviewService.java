package com.review_service.service;

//import java.util.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.review_service.entity.Review;
//import com.review_service.repository.ReviewRepository;
//
//
//
//@Service
//public class ReviewService {
//
//	@Autowired
//	private ReviewRepository reviewRepository;
//
//	public void addReview(Review review) {
//		reviewRepository.save(review);
//	} 
//	 
//	
//}
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.review_service.entity.Review;
import com.review_service.repository.ReviewRepository;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    private static final String REVIEW_SERVICE = "reviewService";

    @CircuitBreaker(name = REVIEW_SERVICE, fallbackMethod = "fallbackAddReview")
    public void addReview(Review review) {
        reviewRepository.save(review); 
    }

    public void fallbackAddReview(Review review, Throwable throwable) {
        System.out.println("Fallback method executed due to: " + throwable.getMessage());

    }
}

