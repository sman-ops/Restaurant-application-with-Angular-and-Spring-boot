package com.spring.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.model.Country;
import com.spring.restaurant.repository.CountryRepository;

@Service
public class CountryService {
	
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getAllCountry(){
		
		return countryRepository.findAll();		
	}

}
