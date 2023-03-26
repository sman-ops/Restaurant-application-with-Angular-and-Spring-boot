package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	

}
