package com.spring.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="client")
public class Client extends PublicData {
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;

}
