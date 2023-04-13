package com.spring.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.service.StateService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class StateController {
	
	
	@Autowired
	private StateService stateService;

}
