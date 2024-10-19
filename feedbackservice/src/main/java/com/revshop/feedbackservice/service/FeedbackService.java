package com.revshop.feedbackservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revshop.feedbackservice.entity.Feedback;
import com.revshop.feedbackservice.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	public Feedback submitFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}
}
