package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {

}
