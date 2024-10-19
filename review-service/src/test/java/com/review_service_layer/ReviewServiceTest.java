//package com.review_service_layer;
//
//import com.review_service.entity.Review;
//import com.review_service.repository.ReviewRepository;
//import com.review_service.service.ReviewService;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.*;
//
//class ReviewServiceTest {
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @InjectMocks
//    private ReviewService reviewService;
//
//    public ReviewServiceTest() {
//        MockitoAnnotations.openMocks(this); 
//    }
//
//    @Test
//    void testAddReview() {
//        Review review = new Review();
//        review.setReviewContent("Amazing product");
//        review.setRating(4);
//
//        
//       
//
//        verify(reviewRepository, times(1)).save(review);
//    }
//}
