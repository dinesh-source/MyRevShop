package com.example.client_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.LoginModel;
import com.example.client_app.model.LoginResponse;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("login", new LoginModel());
        return "Login";
    }

    @PostMapping
    public String processLogin(@ModelAttribute("login") LoginModel loginModel, HttpSession session, Model model) {
        try {
            // Assuming your API returns a UserModel object
            LoginResponse res = restTemplate.postForObject("http://localhost:8090/api/v1/login", loginModel, LoginResponse.class);
            System.out.println("Hello");
            if (res != null) {
                // Email and password are correct; proceed with login
                Integer fetchedUserId = res.getId();
                session.setAttribute("userId", fetchedUserId);

                if ("buyer".equals(res.getRole())) {
                    // If the user is a buyer, redirect to the buyer dashboard
                    return "redirect:/api/v1/buyer-dashboard";
                } else if ("seller".equals(res.getRole())) {
                	session.setAttribute("sellerid", res.getId());
                    return "redirect:/api/v1/seller";
                } else {
                    // Unknown user role
                    model.addAttribute("error", "Unknown user role.");
                    return "Login";
                }
            } else {
                // Invalid login details
                model.addAttribute("error", "Invalid email or password.");
                return "Login";
            }
        } catch (Exception e) {
            // Handle exceptions
            model.addAttribute("error", "An error occurred during login. Please try again.");
            return "Login";
        }
    }
}
