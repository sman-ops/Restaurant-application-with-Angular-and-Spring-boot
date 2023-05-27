package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);

}
