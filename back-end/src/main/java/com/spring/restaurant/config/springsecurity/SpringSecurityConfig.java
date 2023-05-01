package com.spring.restaurant.config.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
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
	
	
	

}
