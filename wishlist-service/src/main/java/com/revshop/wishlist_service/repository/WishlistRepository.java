package com.revshop.wishlist_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.wishlist_service.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
	List<Wishlist> findByUserUserId(Integer userId);
    Wishlist findByUserUserIdAndProductProductId(Integer userId, Integer productId);
}
