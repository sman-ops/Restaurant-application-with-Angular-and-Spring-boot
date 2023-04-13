package com.spring.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restaurant.repository.StateRepository;

@Service
public class StateService {
	
	
	@Autowired
	private StateRepository stateRepository;

}
