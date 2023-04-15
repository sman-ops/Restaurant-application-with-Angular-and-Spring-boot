package com.spring.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order extends PublicData {
	
	@Column(name="price")
	private int price;
	
	@Column(name="image")
	private String  img;
	
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_Category")
	private Category category;

}
