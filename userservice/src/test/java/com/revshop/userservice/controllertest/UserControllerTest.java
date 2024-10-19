//package com.revshop.userservice.controllertest;
//
//
//import com.revshop.userservice.controller.UserController;
//import com.revshop.userservice.entity.SellerModel;
//import com.revshop.userservice.entity.UserModel;
//import com.revshop.userservice.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.ui.Model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class UserControllerTest {
//
//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private Model model;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    void testShowRegistrationForm() {
//        // Create a UserModel instance similar to the controller
//        UserModel expectedUser = new UserModel();
//        expectedUser.setSellermodel(new SellerModel()); // Set up the seller model
//
//        // You don't need to set any properties on the UserModel, 
//        // but make sure the model matches what the controller does.
//        when(userService.saveUser(any(UserModel.class))).thenReturn(expectedUser);
//
//        String viewName = userController.showRegistrationForm(model);
//
//        assertEquals("Register", viewName);
//        verify(model).addAttribute(eq("user"), argThat(user -> {
//            // Check that the user object has the same structure as expected
//            return user instanceof UserModel 
//                    && ((UserModel) user).getSellermodel() instanceof SellerModel;
//        }));
//    }
//
//
//    @Test
//    void testProcessRegistration_SellerMissingDetails() {
//        UserModel userModel = new UserModel();
//        userModel.setUserRole("seller"); // Set role as seller, but no seller details
//        userModel.setSellermodel(null); // Simulate missing seller details
//
//        String result = userController.processRegistration(userModel, model);
//        
//        assertEquals("Register", result);
//        verify(model, times(1)).addAttribute("error", "Seller details are missing");
//    }
//
//    @Test
//    void testProcessRegistration_Buyer() {
//        UserModel userModel = new UserModel();
//        userModel.setUserRole("buyer");
//        userModel.setWalletBalance(0.0); // Ensure the initial balance is set to zero
//
//        String result = userController.processRegistration(userModel, model);
//        
//        assertEquals("redirect:/api/v1/login", result);
//        verify(userService, times(1)).saveUser(userModel);
//        assertEquals(5500.0, userModel.getWalletBalance());
//    }
//}
