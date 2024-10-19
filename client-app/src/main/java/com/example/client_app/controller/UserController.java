package com.example.client_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.client_app.model.SellerModel;
import com.example.client_app.model.UserModel;



@Controller
@RequestMapping("/api/v1/register")
public class UserController {
	
	@Autowired
	 private RestTemplate restTemplate;

    @GetMapping
    public String showRegistrationForm(Model model) {
        UserModel user = new UserModel();
        SellerModel seller = new SellerModel();
        user.setSellermodel(seller);
        model.addAttribute("user", user);
        return "Register";
    }

    @PostMapping
    public String processRegistration(
            @ModelAttribute("user") UserModel userModel,Model model) {
    	try {
    		restTemplate.postForObject("http://localhost:8090/api/v1/register", userModel, String.class);
    		return "redirect:/api/v1/login";
    	} catch(Exception e) {
    		model.addAttribute("error", e.getMessage());
    		return "Register";
    	}
    }
}
