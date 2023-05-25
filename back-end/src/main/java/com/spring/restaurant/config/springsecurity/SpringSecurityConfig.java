package com.spring.restaurant.config.springsecurity;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.spring.restaurant.config.springsecurity.jwt.JwtAuthorizationFilter;
import com.spring.restaurant.repository.UserRepository;
import com.spring.restaurant.service.UserService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	 private UserService userService;
	 private UserRepository userRepository;
	 
	  @Autowired
	    public SpringSecurityConfig(UserService userService, UserRepository userRepository) {
	        this.userService = userService;
	        this.userRepository = userRepository;
	    }
	
	 @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	
	 @Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// csrf prevent any requests comes from frontend
		http
			
		   	.cors()
		   	.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
			.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
			.antMatchers("/signin").permitAll()
			.antMatchers("/signup").permitAll()
			.anyRequest().authenticated();
			
		 // .antMatchers("/api/allOrders").permitAll()
			
	}
	
	 @Bean
	  public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    // Don't do this in production, use a proper list  of allowed origins
	    config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	    source.registerCorsConfiguration("/**", config);
	    // some comment here
	    return new CorsFilter(source);
	  }

	
	
	
	 @Bean
	    DaoAuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        daoAuthenticationProvider.setUserDetailsService(userService);
	        return daoAuthenticationProvider;
	    }
	    @Bean
	    PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	
	
	

}
