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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/")
public class OrderController {
	
	private OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	
	 // http://localhost/api/allOrders?page={value}&size={value}
	
	@GetMapping("allOrders")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam int page,@RequestParam int size){
		List<Order> orders= orderService.allOrders(page,size);
		return new  ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	//http://localhost:8080/api/category?id={value}&page={value}&size={value}
	@GetMapping("category")
	public ResponseEntity<List<Order>> getAllOrdersByCategoryId(@RequestParam Long id,@RequestParam int page,@RequestParam int size){
		
		List<Order> orders= orderService.getOrdersByIdCategories(id,page,size);
		return new  ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	@GetMapping("orderKey")
	public ResponseEntity<List<Order>> getOrdersByKey(@RequestParam String word,@RequestParam int page,@RequestParam int size){
		List<Order> orders= orderService.getOrdersByKey(word,page,size);
		
		return new  ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	// http://locahost:8080/api/order?id={value}
	@GetMapping("order") 
	public Order getOrderById(@RequestParam Long id) {
		return orderService.getOrder(id);
	
	}
	@GetMapping("orderSize") 
	public long orderSize() {
		 return orderService.getAllOrdersSize();
	}
	
	@GetMapping("categoryidSize") 
	public long getOrderByIdCategorySize(@RequestParam Long id) {
		 return orderService.getOrderByCategoryIdLength(id);
	}
	
	@GetMapping("keySize") 
	public long sizeOfOrderByKey(@RequestParam String key) {
		 return orderService.getOrderSizeByKey(key);
	}
	
	
	
	//http://localhost:8080/api/category?id={value}
	//	@GetMapping("/api/category/{id}")
		//public ResponseEntity<List<Order>> getAllOrdersByCategoryId(@PathVariable Long id){
			
		//	List<Order> orders= orderService.getOrdersByIdCategories(id);
			//return new  ResponseEntity<>(orders,HttpStatus.OK);
			
		//}
	
	
}
