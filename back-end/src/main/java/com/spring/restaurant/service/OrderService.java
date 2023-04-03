package com.spring.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.model.Order;
import com.spring.restaurant.repository.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepository;
	
	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository=orderRepository;
	}
	
	public List<Order> allOrders(){
		
		return orderRepository.findAll();
		
	}
	
	public List<Order> getOrdersByIdCategories(Long id){
		
		return orderRepository.findByCategoryId(id);
		
	}
	
	public List<Order> getOrdersByKey(String key){
		return orderRepository.findByNameContaining(key);
	}
	
	public Order getOrder(Long id) {
		return orderRepository.findById(id).get();	
		}

}
