package com.spring.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.restaurant.model.Authorities;
import com.spring.restaurant.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Transactional(readOnly = true)
	public List<Authorities> getAuthorities() {
		return authoritiesRepository.findAll();
	}

}
