package com.spring.restaurant.config.springsecurity.jwt;
import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.restaurant.dto.JwtLogin;
import com.spring.restaurant.dto.LoginResponse;
import com.spring.restaurant.dto.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


@Service
public class JwtAuthenticationFilter  {
	
	  private AuthenticationManager authenticationManager;
	  	
	  
	    @Autowired
	    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }
	    
	  private String generateToken(Authentication authResult) {
	    	

	    	UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
	         System.out.println(principal.getUsername());

	         // Create JWT Token
	         String token = JWT.create()
	                 .withSubject(principal.getUsername())
	                 .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
	                 .sign(HMAC512(JwtProperties.SECRET.getBytes()));
	    
	    return token;
	    
	    }
	  
	  public LoginResponse login(JwtLogin jwtLogin) {
		  
		  // Authenticate user
	        Authentication authenticate = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(jwtLogin.getEmail(),
	        		
	        		jwtLogin.getPassword()));
	        		
	        SecurityContextHolder.getContext().setAuthentication(authenticate);
	        String token = generateToken(authenticate);
	        
	        return new LoginResponse(jwtLogin.getEmail(),token);
	  }

	  
}
