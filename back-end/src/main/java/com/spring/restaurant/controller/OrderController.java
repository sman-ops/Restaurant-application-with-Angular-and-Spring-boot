package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.model.Order;
import com.spring.restaurant.service.OrderService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/")
public class OrderController {
	
	private OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	
	
	@GetMapping("allOrders")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders= orderService.allOrders();
		return new  ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	//http://localhost:8080/api/category?id={value}
	@GetMapping("category")
	public ResponseEntity<List<Order>> getAllOrdersByCategoryId(@RequestParam Long id){
		
		List<Order> orders= orderService.getOrdersByIdCategories(id);
		return new  ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	@GetMapping("orderKey")
	public ResponseEntity<List<Order>> getOrdersByKey(@RequestParam String word){
		List<Order> orders= orderService.getOrdersByKey(word);
		
		return new  ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	//http://localhost:8080/api/category?id={value}
	//	@GetMapping("/api/category/{id}")
		//public ResponseEntity<List<Order>> getAllOrdersByCategoryId(@PathVariable Long id){
			
		//	List<Order> orders= orderService.getOrdersByIdCategories(id);
			//return new  ResponseEntity<>(orders,HttpStatus.OK);
			
		//}
	
	
}
