package com.spring.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.restaurant.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

}
