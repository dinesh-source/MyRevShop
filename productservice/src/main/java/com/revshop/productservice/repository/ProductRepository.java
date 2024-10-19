package com.revshop.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revshop.productservice.entity.Product;
import com.revshop.productservice.enums.Gender;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	 List<Product> findByGenderInAndPriceBetween(List<Gender> genders, double minPrice, double maxPrice);
	    
	    List <Product> findByPriceBetween(double minPrice, double maxPrice);

	    List<Product> findAllByCategory_CategoryIdIn(List<Integer> categoryIds);
	    
	    List<Product> findByGenderInAndCategory_CategoryIdIn(List<Gender> genders, List<Integer> categoryIds);
	    
	    List<Product> findByCategory_CategoryIdInAndSize_SizeIdInAndPriceBetween(
	    		List<Integer> categoryId, List<Integer> sizeId, double minPrice, double maxPrice);
	    
	    List<Product> findByGenderInAndCategory_CategoryIdInAndSize_SizeIdInAndPriceBetween(List<Gender> genders, List<Integer> categoryIds, List<Integer> sizeId, double minPrice, double maxPrice);
			
	    List<Product> findByGenderInAndCategory_CategoryIdInAndSize_SizeIdIn(List<Gender> genders, List<Integer> categoryIds, List<Integer> sizeId);

	    List<Product> findBySellerModel_SellerId(int sellerid);
}
