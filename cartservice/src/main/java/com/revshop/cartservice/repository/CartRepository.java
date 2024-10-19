package com.revshop.cartservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revshop.cartservice.entity.*;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUserModel(UserModel userModel);

	Optional<Cart> findByUserModelAndProduct(UserModel user, Product product);

	void deleteByUserModel(UserModel user);

	Cart findByUserModelUserIdAndProductProductId(Integer userId, Integer productId);
}
