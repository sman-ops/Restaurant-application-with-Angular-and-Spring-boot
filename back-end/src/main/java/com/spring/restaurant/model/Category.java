package com.spring.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
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
public class Category extends PublicData {
	
	@Column(name="categorylogo")
	private String logo;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Order> orders;
	

	
}
