//package com.revshop.userservice.controllertest;
//
//import com.revshop.userservice.controller.LoginController;
//import com.revshop.userservice.entity.LoginModel;
//import com.revshop.userservice.entity.SellerModel;
//import com.revshop.userservice.entity.UserModel;
//import com.revshop.userservice.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.ui.Model;
//import jakarta.servlet.http.HttpSession;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//
//public class LoginControllerTest {
//
//    @InjectMocks
//    private LoginController loginController;
//
//    @Mock
//    private UserService userservice;
//
//    @Mock
//    private HttpSession session;
//
//    @Mock
//    private Model model;
//
//    private LoginModel loginModel;
//    private UserModel userModel;
//    private SellerModel sellerModel;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Setting up test data
//        loginModel = new LoginModel();
//        loginModel.setUserEmail("test@example.com");
//        loginModel.setUserPassword("password");
//
//        userModel = new UserModel();
//        userModel.setUserId(1);
//        userModel.setUserRole("seller");
//
//        sellerModel = new SellerModel();
//        sellerModel.setSellerId(1);
//    }
//
//    @Test
//    public void testShowLoginForm() {
//        // Setup mock model
//        Model model = Mockito.mock(Model.class);
//        LoginModel expectedLoginModel = new LoginModel(); // Initialize as needed
//
//        // Call method under test
//        loginController.showLoginForm(model);
//
//        // Verify that the correct method was called with an instance of LoginModel
//        Mockito.verify(model).addAttribute(eq("login"), any(LoginModel.class));
//    }
//
//    @Test
//    public void testProcessLogin_UserNotFound() {
//        when(userservice.findByEmail("test@example.com")).thenReturn(null);
//
//        String result = loginController.processLogin(loginModel, session, model);
//        assertEquals("Login", result);
//        verify(model).addAttribute("error", "Invalid email. Please try again.");
//    }
//
//    @Test
//    public void testProcessLogin_IncorrectPassword() {
//        when(userservice.findByEmail("test@example.com")).thenReturn(userModel);
//        when(userservice.isPasswordCorrect(userModel, "wrongpassword")).thenReturn(false);
//
//        loginModel.setUserPassword("wrongpassword");
//        String result = loginController.processLogin(loginModel, session, model);
//        assertEquals("Login", result);
//        verify(model).addAttribute("error", "Incorrect password. Please try again.");
//    }
//
//    @Test
//    public void testProcessLogin_SuccessfulLogin_AsSeller() {
//        when(userservice.findByEmail("test@example.com")).thenReturn(userModel);
//        when(userservice.isPasswordCorrect(userModel, "password")).thenReturn(true);
//        when(userservice.getSellerId(1)).thenReturn(sellerModel);
//
//        String result = loginController.processLogin(loginModel, session, model);
//        assertEquals("redirect:/api/v1/seller", result);
//        verify(session).setAttribute("userId", userModel.getUserId());
//        verify(session).setAttribute("sellerid", sellerModel.getSellerId());
//    }
//
//    @Test
//    public void testProcessLogin_NoSellerAccount() {
//        when(userservice.findByEmail("test@example.com")).thenReturn(userModel);
//        when(userservice.isPasswordCorrect(userModel, "password")).thenReturn(true);
//        when(userservice.getSellerId(1)).thenReturn(null);
//
//        String result = loginController.processLogin(loginModel, session, model);
//        assertEquals("Login", result);
//        verify(model).addAttribute("error", "No seller account found for this user.");
//    }
//}
