package com.spring.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.spring.restaurant.model.Country;
import com.spring.restaurant.service.CountryService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class CountryController {
	
	
	@Autowired
	private CountryService countryService;
	
	
	@GetMapping("/countries")
	public List<Country> getAllCountry(){
		return countryService.getAllCountry();
		
	}

}
