package com.spring.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.config.springsecurity.jwt.JwtAuthenticationFilter;
import com.spring.restaurant.dto.JwtLogin;
import com.spring.restaurant.dto.LoginResponse;
import com.spring.restaurant.model.User;
import com.spring.restaurant.service.AuthoritiesService;
import com.spring.restaurant.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping
public class UserController {
	
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthoritiesService authoritiesService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/signin")
	public LoginResponse logIn(@RequestBody JwtLogin jwtLogin) {
		
		return jwtAuthenticationFilter.login(jwtLogin);
		
	}
	
	@PostMapping("/signup")
	public void createUser(@RequestBody JwtLogin jwtLogin) {
		User user= new User();
		user.setEmail(jwtLogin.getEmail());
		user.setPassword(passwordEncoder.encode(jwtLogin.getPassword()));
		user.setActive(1);
		user.getAuthorities().add(authoritiesService.getAuthorities().get(0));
		user.getAuthorities().add(authoritiesService.getAuthorities().get(1));
		userService.addUser(user);
		
		
	}

}
