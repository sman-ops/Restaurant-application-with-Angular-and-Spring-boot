package com.spring.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="addresse")
public class Address extends CategoryOrder {
	
	@Column(name="country")
	private String country;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip_code")
	private String zipCode;
	
	
	@Column(name="to_country")
	private String tocountry;
	
	@Column(name="to_state")
	private String tostate;
	
	@Column(name="to_zipcode")
	private String toZipCode;
	
	 @OneToOne
	 private RequestOrder requestOrder;
	
	

}
