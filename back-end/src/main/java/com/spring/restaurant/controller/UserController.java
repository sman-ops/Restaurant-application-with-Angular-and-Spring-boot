package com.spring.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.config.springsecurity.jwt.JwtAuthenticationFilter;
import com.spring.restaurant.dto.JwtLogin;
import com.spring.restaurant.dto.LoginResponse;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping
public class UserController {
	
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@PostMapping("/login")
	public LoginResponse logIn(@RequestBody JwtLogin jwtLogin) {
		
		return jwtAuthenticationFilter.login(jwtLogin);
		
	}

}
