package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.State;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {

}
