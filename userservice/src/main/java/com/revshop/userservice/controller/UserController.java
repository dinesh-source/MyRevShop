package com.revshop.userservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.userservice.entity.SellerModel;
import com.revshop.userservice.entity.UserModel;
import com.revshop.userservice.service.UserService;


@RestController
@RequestMapping("/api/v1/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> processRegistration(
            @RequestBody UserModel userModel) {
 	
        if ("seller".equals(userModel.getUserRole())) {
            SellerModel seller = userModel.getSellermodel();
            if (seller != null) {
                seller.setUsermodel(userModel);
                UserModel savedUser = userService.saveUser(userModel);
                seller.setUsermodel(savedUser); 
                userService.saveSeller(seller);
            } else {
                return ResponseEntity.badRequest().body("");
            }
        } else {
            userModel.setWalletBalance(5500.0);
            userService.saveUser(userModel); 
        }
        return ResponseEntity.ok("User registred successfully");
       
    }

}

