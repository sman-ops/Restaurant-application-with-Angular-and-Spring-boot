package com.spring.restaurant.dto;


import java.util.List;
import java.util.Set;

import com.spring.restaurant.model.Address;
import com.spring.restaurant.model.Client;
import com.spring.restaurant.model.Item;
import com.spring.restaurant.model.RequestOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
	
	private Client client;
	private RequestOrder requestOrder;
	private List<Item> items;
	private Address fromAddress;
	

}
