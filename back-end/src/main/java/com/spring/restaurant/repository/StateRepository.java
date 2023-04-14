package com.spring.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.State;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {
	
	public List<State> findByCountryCode(String code);

}
