package com.spring.restaurant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
	
	public Page<Order> findByCategoryId(Long id,Pageable pageable);
	//Like '%name%'
	public  Page<Order> findByNameContaining(String name,Pageable pageable);
	
	
	
		


}
