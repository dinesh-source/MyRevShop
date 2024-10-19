package com.revshop.order_service.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.order_service.entity.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	UserModel findByUserId(int userid);
}
