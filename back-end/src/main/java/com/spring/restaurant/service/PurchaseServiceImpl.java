package com.spring.restaurant.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.restaurant.dto.PurchaseRequest;
import com.spring.restaurant.dto.PurchaseResponse;
import com.spring.restaurant.model.RequestOrder;
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
		
		Set<com.spring.restaurant.model.Item> items= purchases.getItems();
		items.forEach(item-> requestOrder.addItem(item));
		
		
		
		requestOrder.setFromAddress(purchases.getFromAddress());
		requestOrder.setToAddress(purchases.getToAddress());
		
		
		// Set<RequestOrder> requestOrders= purchases
		// requestOrders.add(requestOrder);
		// purchases.getClient().setRequestOrders(requestOrders);
		// requestOrder.setClient(purchases.getClient());
		
		purchases.getClient().addRequestOrder(requestOrder);
		
		clientRepository.save(purchases.getClient());
		
		
		return new PurchaseResponse(purchases.getClient().getName(),myCode);
		
		
		
		
	}
	
	private  String getCode() {
		return UUID.randomUUID().toString();
	}
	
	

}
