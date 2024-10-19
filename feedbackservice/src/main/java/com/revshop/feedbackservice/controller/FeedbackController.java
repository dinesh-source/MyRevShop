package com.revshop.feedbackservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.feedbackservice.entity.Feedback;
import com.revshop.feedbackservice.service.FeedbackService;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping
	public ResponseEntity<?> submitFeedback(@RequestBody Feedback feedback, Model model) {
		feedbackService.submitFeedback(feedback);
		return ResponseEntity.ok(200);
	}
}
