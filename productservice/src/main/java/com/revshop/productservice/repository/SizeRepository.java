package com.revshop.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.productservice.entity.Category;
import com.revshop.productservice.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
	List<Size> findByCategory(Optional<Category> category);
	
    List<Size> findByCategory_CategoryIdIn(List<Integer> categoryId);

}

