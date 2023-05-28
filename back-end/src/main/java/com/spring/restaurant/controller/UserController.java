package com.spring.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restaurant.config.springsecurity.jwt.JwtAuthenticationFilter;
import com.spring.restaurant.dto.AccountResponse;
import com.spring.restaurant.dto.JwtLogin;
import com.spring.restaurant.dto.LoginResponse;
import com.spring.restaurant.dto.Mail;
import com.spring.restaurant.model.Code;
import com.spring.restaurant.model.User;
import com.spring.restaurant.service.AuthoritiesService;
import com.spring.restaurant.service.EmailService;
import com.spring.restaurant.service.UserService;
import com.spring.restaurant.util.UserCode;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	
	@Autowired
	private EmailService emailService;
	
	private UserCode userCode = new UserCode();
	
	
	@PostMapping("/signin")
	public LoginResponse logIn(@RequestBody JwtLogin jwtLogin) {
		
		return jwtAuthenticationFilter.login(jwtLogin);
		
	}
	
	@PostMapping("/signup")
	public AccountResponse createUser(@RequestBody JwtLogin jwtLogin) {
		AccountResponse accountResponse = new AccountResponse();
		boolean result = userService.ifEmailExists(jwtLogin.getEmail());
		if(result) {
			
			accountResponse.setResult(0);
			
			
		}else {
			String myCode = userCode.getCode();
			User user= new User();
			user.setEmail(jwtLogin.getEmail());
			user.setPassword(passwordEncoder.encode(jwtLogin.getPassword()));
			user.setActive(0);
			user.getAuthorities().add(authoritiesService.getAuthorities().get(0));
			Mail mail = new Mail(jwtLogin.getEmail(),myCode);
		    emailService.sendCodeByMail(mail);
		    Code code = new Code();
		    code.setCode(myCode);
		    user.setCode(code);
			userService.addUser(user);
			accountResponse.setResult(1);
		}
		
		return accountResponse;
		
		
		
	}

}
