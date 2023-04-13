package com.spring.restaurant.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class PublicData extends BaseEntity {
	
	 @Column(name="name")
	private String name;

}


//@MappedSuperClass pour définier à Hibernate que j'ai hérité de cette classe'