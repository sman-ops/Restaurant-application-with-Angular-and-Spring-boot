package com.spring.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.dto.PurchaseRequest;
import com.spring.restaurant.dto.PurchaseResponse;
import com.spring.restaurant.service.PurchaseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/buy")
public class PurchasesController {
	
	
	@Autowired
	private PurchaseService purchaseService;
	
	
	@PostMapping("/purchase")
	public PurchaseResponse addRequestOrder(@RequestBody PurchaseRequest purchaseRequest) {
		return purchaseService.addRequestOrder(purchaseRequest);
		
	}
	

}
