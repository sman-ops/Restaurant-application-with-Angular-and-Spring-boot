package com.spring.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category extends CategoryOrder {
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Order> orders;
	

	
}
