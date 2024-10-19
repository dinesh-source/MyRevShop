package com.revshop.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revshop.userservice.dto.LoginResponse;
import com.revshop.userservice.entity.LoginModel;
import com.revshop.userservice.entity.SellerModel;
import com.revshop.userservice.entity.UserModel;
import com.revshop.userservice.service.UserService;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> processLogin(@RequestBody LoginModel loginModel) {
        UserModel user = userService.findByEmail(loginModel.getUserEmail());
        LoginResponse res = new LoginResponse();

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email. Please try again.");
        } else if (!userService.isPasswordCorrect(user, loginModel.getUserPassword())) {
            return ResponseEntity.badRequest().body("Incorrect password. Please try again.");
        }

        Integer fetchedUserId = user.getUserId();

        if ("buyer".equals(user.getUserRole())) {
        	res.setId(user.getUserId());
        	res.setRole(user.getUserRole());
            return ResponseEntity.ok(res);
        } else if ("seller".equals(user.getUserRole())) {
            SellerModel seller = userService.getSellerId(fetchedUserId);
            if (seller != null) {
            	res.setId(user.getUserId());
            	res.setRole(user.getUserRole());
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.badRequest().body("No seller account found for this user.");
            }
        } else {
            return ResponseEntity.badRequest().body("Unknown user role.");
        }
    }
    
    @GetMapping("/api/v1/user")
    public ResponseEntity<?> getUserModel(@RequestParam("userId") Integer userId){
    	UserModel userModel = userService.getUserId(userId);
    	return ResponseEntity.ok(userModel);
    }
}
