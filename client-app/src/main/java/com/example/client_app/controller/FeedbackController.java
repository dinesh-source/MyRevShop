package com.example.client_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.*;

@Controller
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

	@Autowired
    private RestTemplate restTemplate;
	
	@GetMapping
	public String showFeedbackForm(Model model) {
		model.addAttribute("feedback", new Feedback());
		return "feedback";
	}
	
	@PostMapping
	public String submitFeedback(@RequestParam("comments") String comment, @RequestParam("username") String username,
			@RequestParam("rating") Integer rating, Model model) {
		Feedback feedback = new Feedback();
		feedback.setComments(comment);
		feedback.setRating(rating);
		feedback.setUsername(username);
		Integer status = restTemplate.postForObject("http://localhost:9002/api/v1/feedback", feedback, Integer.class);
		if(status == 200) {			
			model.addAttribute("message", "Thank you for your feedback!");
			return "feedback_success";
		}
		else {
			return "notfound";
		}
	}

}
