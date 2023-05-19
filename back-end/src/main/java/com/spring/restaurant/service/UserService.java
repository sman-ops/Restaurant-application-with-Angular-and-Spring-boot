package com.spring.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.restaurant.dto.JwtLogin;
import com.spring.restaurant.dto.UserPrincipal;
import com.spring.restaurant.model.User;
import com.spring.restaurant.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		System.out.println(user.getEmail() + "" +user.getPassword());
		// container of user data
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}
	
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
		
	}

}
