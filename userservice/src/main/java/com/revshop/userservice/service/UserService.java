package com.revshop.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.userservice.entity.SellerModel;
import com.revshop.userservice.entity.UserModel;
import com.revshop.userservice.repository.SellerRepository;
import com.revshop.userservice.repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }
    
    public SellerModel saveSeller(SellerModel sellermodel) {    	
    	return sellerRepository.save(sellermodel);   	
    }
    
    // First check if the email exists
    public UserModel findByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    // Check if the password matches
    public boolean isPasswordCorrect(UserModel user, String userPassword) {
        return user != null && user.getUserPassword().equals(userPassword);
    }
    
    
    public SellerModel getSellerId(Integer userID) {
		return sellerRepository.findByUsermodelUserId(userID);
	}

	public void updatePassword(String password, String key) {
		UserModel userModel = userRepository.findByUserEmail(key);
		userModel.setUserPassword(password);
		userRepository.save(userModel);
	}

	public UserModel getUserId(int userId) {
		return userRepository.findByUserId(userId);
	}
	
	public void updateUserProfile(UserModel user) {
		userRepository.save(user);
	}
}

