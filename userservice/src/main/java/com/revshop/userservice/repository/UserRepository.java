package com.revshop.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.userservice.entity.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	 UserModel findByUserEmailAndUserPassword(String userEmail, String userPassword);	
	 UserModel findByUserEmail(String userEmail);
	 UserModel findByUserId(int userId);
}
