package com.spring.restaurant.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
@Table(name="request_orderss")
public class RequestOrder extends CategoryOrder {
	
	
	@Column(name="code")
	private String code ;
	
	
	
	@Column(name="total_price")
	private int totalPrice;
	
	@Column(name="total_quantity")
	private int totalQuantity;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="requestOrder")
	private List<Item> items=new ArrayList();
	
	
	@ManyToOne()
	@JoinColumn(name="client_id")
	private Client client=new Client();
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="from_address_id")
	private Address fromAddress=new Address();
	
	//@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="to_address_id",referencedColumnName="id")
	//private Address toAddress=new Address();
	
	public void addItem(Item item) {
		items.add(item);
		item.setRequestOrder(this);
	}
	
}
