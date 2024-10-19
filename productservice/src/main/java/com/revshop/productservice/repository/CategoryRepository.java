package com.revshop.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.productservice.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
