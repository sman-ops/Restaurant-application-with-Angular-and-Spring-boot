package com.spring.restaurant.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name="client")
public class Client extends PublicData {
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="client")
	private Set<RequestOrder> requestOrders=new HashSet<>();
	
	public void addRequestOrder(RequestOrder requestOrder) {
		requestOrders.add(requestOrder);
		requestOrder.setClient(this);
		
	}

}
