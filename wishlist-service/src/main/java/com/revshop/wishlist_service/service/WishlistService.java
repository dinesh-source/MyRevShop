package com.revshop.wishlist_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.wishlist_service.entity.Product;
import com.revshop.wishlist_service.entity.Wishlist;
import com.revshop.wishlist_service.repository.ProductRepository;
import com.revshop.wishlist_service.repository.UserRepository;
import com.revshop.wishlist_service.repository.WishlistRepository;

@Service
public class WishlistService {
	
	@Autowired
    private WishlistRepository wishlistRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void addToWishlist(Integer userId, Integer productId) throws IllegalArgumentException{
        if (wishlistRepository.findByUserUserIdAndProductProductId(userId, productId) != null) {
            throw new IllegalArgumentException("Product already in wishlist");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found")));
        wishlist.setProduct(productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found")));

        wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(Integer userId, Integer productId) throws IllegalArgumentException {
        Wishlist wishlist = wishlistRepository.findByUserUserIdAndProductProductId(userId, productId);
        if (wishlist == null) {
            throw new IllegalArgumentException("Product not in wishlist");
        }
        wishlistRepository.delete(wishlist);
    }

    public List<Product> getWishlist(Integer userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUserUserId(userId);
        return wishlistItems.stream()
                .map(Wishlist::getProduct)
                .collect(Collectors.toList());
    }
}
