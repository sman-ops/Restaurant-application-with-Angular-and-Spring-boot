package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.model.Order;
import com.spring.restaurant.service.OrderService;

@RestController
public class OrderController {
	
	private OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@GetMapping("/api/allOrders")
	public List<Order> getAllOrders(){
		return orderService.allOrders();
		
	}

}
