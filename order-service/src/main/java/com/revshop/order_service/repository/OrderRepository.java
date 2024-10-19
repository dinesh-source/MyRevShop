package com.revshop.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.order_service.entity.Order;
import com.revshop.order_service.entity.UserModel;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByUserModelOrderByOrderDateDesc(UserModel userModel);
}
