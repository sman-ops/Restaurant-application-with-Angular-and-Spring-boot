package com.spring.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
	
	public List<Order> findByCategoryId(Long id);

}
