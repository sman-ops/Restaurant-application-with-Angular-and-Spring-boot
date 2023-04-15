package com.spring.restaurant.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="requestOrder")
	private Set<Item> items;
	
	
	@ManyToOne()
	@JoinColumn(name="client_id")
	private Client client;
	
	
	@OneToOne()
	@JoinColumn(name="to_address_id",referencedColumnName="id")
	private Address toAddress; 
	
	@OneToOne()
	@JoinColumn(name="from_address_id",referencedColumnName="id")
	private Address fromAddress; 
	


}
