package com.revshop.wishlist_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.wishlist_service.entity.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

}
