package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.model.State;
import com.spring.restaurant.service.StateService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class StateController {
	
	
	@Autowired
	private StateService stateService;
	
	
	@GetMapping("/states")
	public List<State> getListStates(){
		
		return stateService.getListStates();
		
	}
	// localhost:8080/api/statescode?code=TN
	@GetMapping("/statescode")
	public List<State> getStatesByCode(@RequestParam String code){
		
		return stateService.getStatesByCountryCode(code);
		
		
		
	}

}
