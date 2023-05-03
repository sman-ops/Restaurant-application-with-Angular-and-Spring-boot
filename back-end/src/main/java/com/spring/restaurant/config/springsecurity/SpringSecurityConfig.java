package com.spring.restaurant.config.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.restaurant.service.UserService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserService userService;
	
	@Override
	public void configure (AuthenticationManagerBuilder auth ) throws Exception{
		
		auth.authenticationProvider(authentificationProvider());
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// csrf prevent any requests comes from frontend
		http
			.csrf().disable()
			.authorizeRequests()
			.anyRequest().permitAll()
			.and()
			.httpBasic();
	}
	
	@Bean
	DaoAuthenticationProvider authentificationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	 daoAuthenticationProvider.setUserDetailsService(userService);
		return daoAuthenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	

}