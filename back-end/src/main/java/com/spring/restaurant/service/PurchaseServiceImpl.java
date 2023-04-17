package com.spring.restaurant.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.restaurant.dto.PurchaseRequest;
import com.spring.restaurant.dto.PurchaseResponse;
import com.spring.restaurant.model.RequestOrder;
import com.spring.restaurant.model.Item;
import com.spring.restaurant.repository.ClientRepository;


@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional
	public PurchaseResponse addRequestOrder(PurchaseRequest purchases) {
		
		RequestOrder requestOrder=purchases.getRequestOrder();
		String myCode=getCode();
		requestOrder.setCode(myCode);
		
		List<Item> items= purchases.getItems();
		items.forEach(item -> requestOrder.addItem(item));

		requestOrder.setFromAddress(purchases.getFromAddress());
		
		purchases.getClient().addRequestOrder(requestOrder);
		
		clientRepository.save(purchases.getClient());
		
		return new PurchaseResponse(purchases.getClient().getName(),myCode);
		
		
		
		
	}
	
	private  String getCode() {
		return UUID.randomUUID().toString();
	}
	
	

}
