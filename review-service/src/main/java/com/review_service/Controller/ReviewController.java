package com.review_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.review_service.entity.Review;
import com.review_service.service.ReviewService;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addReview(@RequestBody Review review) {
		reviewService.addReview(review);
		return ResponseEntity.ok(200);
	}		
}
	



