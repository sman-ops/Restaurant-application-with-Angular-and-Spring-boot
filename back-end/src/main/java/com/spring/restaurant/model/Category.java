package com.spring.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category extends CategoryOrder {
	
	@OneToMany(mappedBy="category")
	private Set<Order> orders;
	

	
}
