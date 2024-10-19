//package com.review_service.Controller;
//
//
//
//import com.review_service.entity.Review;
//import com.review_service.service.ProductService;
//import com.review_service.service.ReviewService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.ui.Model;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ReviewController.class) 
//class ReviewControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private ReviewService reviewService;
//
//    @Mock
//    private ProductService productService;
//
//    @InjectMocks
//    private ReviewController reviewController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this); // Initialize the mock objects
//        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
//    }
//
//    @Test
//    void testDisplayReviewForm() throws Exception {
//       
//        mockMvc.perform(get("/api/v1/reviews/1"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("showReview"))
//            .andExpect(model().attributeExists("review"))
//            .andExpect(model().attributeExists("productId"))
//            .andExpect(model().attribute("productId", 1));
//    }
//
//    @Test
//    void testAddReviewSuccess() throws Exception {
//      
//        Product product = new Product();
//        when(productService.findById(anyInt())).thenReturn(product);
//
//      
//        doNothing().when(reviewService).addReview(any(Review.class));
//
//        mockMvc.perform(post("/api/v1/reviews/1")
//            .param("reviewContent", "Amazing product!")
//            .param("rating", "5")
//            .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//            .andExpect(status().is3xxRedirection()) 
//            .andExpect(redirectedUrl("/api/v1/orders/history")); 
//    }
//
//    @Test
//    void testAddReviewNotFound() throws Exception {
//        
//        when(productService.findById(anyInt())).thenReturn(null);
//
//        
//        mockMvc.perform(post("/api/v1/reviews/1")
//            .param("reviewContent", "Amazing product!")
//            .param("rating", "5")
//            .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//            .andExpect(status().isOk())
//            .andExpect(view().name("notfound")); 
//}
//}
