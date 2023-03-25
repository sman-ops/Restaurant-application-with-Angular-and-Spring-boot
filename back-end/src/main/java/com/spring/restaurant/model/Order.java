package com.spring.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order")
public class Order extends CategoryOrder {
	
	@Column(name="price")
	private int price;
	@Column(name="image")
	private String  img;
	@Column(name="description")
	private String description;

}
