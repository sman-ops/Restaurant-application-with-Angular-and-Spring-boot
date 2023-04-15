package com.spring.restaurant.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name="request_order")
public class RequestOrder extends CategoryOrder {
	
	
	@Column(name="code")
	private String code ;
	
	@Column(name="note")
	@Lob
	private String note;
	
	@Column(name="total_price")
	private int totalPrice;
	
	@Column(name="total_quantity")
	private int totalQuantity;
	


}
