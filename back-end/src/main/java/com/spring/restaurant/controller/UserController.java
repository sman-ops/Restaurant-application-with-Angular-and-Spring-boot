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
import com.spring.restaurant.dto.ActiveAccount;
import com.spring.restaurant.dto.JwtLogin;
import com.spring.restaurant.dto.LoginResponse;
import com.spring.restaurant.dto.Mail;
import com.spring.restaurant.dto.NewPassword;
import com.spring.restaurant.dto.ResetPassword;
import com.spring.restaurant.dto.UserActive;
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
	
	@PostMapping("/active")
	public UserActive getActiveUser(@RequestBody JwtLogin jwtlogin) {
		String enPassword = userService.getPasswordByEmail(jwtlogin.getEmail()); // from DB
		boolean result = passwordEncoder.matches(jwtlogin.getPassword(),enPassword);
		UserActive userActive = new UserActive();
		if(result) {
			int act = userService.getUserActive(jwtlogin.getEmail());
			userActive.setActive(act);
			
		}else {
			userActive.setActive(-1);
			
		}
		return userActive;
	}
	

	@PostMapping("/activated")
	public AccountResponse activeAccount(@RequestBody ActiveAccount activeAccount) {
		User user = userService.getUserByEmail(activeAccount.getMail());
		AccountResponse accountResponse = new AccountResponse();
		if(user.getCode().getCode().equals(activeAccount.getCode())) {
			user.setActive(1);
			userService.editUser(user);
			accountResponse.setResult(1);
		}else {
			accountResponse.setResult(0);
		}
		return accountResponse;
	}
	
	@PostMapping("/checkEmail")
	public AccountResponse resetPasswordEmail( @RequestBody ResetPassword resetPassword) {
		
		User user =this.userService.getUserByEmail(resetPassword.getEmail());
		AccountResponse accountResponse = new AccountResponse();
		if(user != null) {
			String myCode = userCode.getCode();
			Mail mail = new Mail(resetPassword.getEmail(),myCode);
			emailService.sendCodeByMail(mail);
			user.getCode().setCode(myCode);
			this.userService.editUser(user);
			accountResponse.setResult(1);
		}else {
			accountResponse.setResult(0);
		}
		return accountResponse;
		
	}
	@PostMapping("/resetPassword")
  public AccountResponse resetPassword(@RequestBody NewPassword newPassword) {
		
		User user =this.userService.getUserByEmail(newPassword.getEmail());
		AccountResponse accountResponse = new AccountResponse();
		if(user!=null) {
			if(user.getCode().getCode().equals(newPassword.getCode())) {
				user.setPassword(passwordEncoder.encode(newPassword.getPassword()));
				userService.addUser(user);
				accountResponse.setResult(1);
			}else {
				accountResponse.setResult(0);
				
			}
			
		}else {
			accountResponse.setResult(0);
			
		}
		return accountResponse;
		
		
		
		
	  
  }

}
