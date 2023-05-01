package com.spring.restaurant.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authorities")
public class Authorities extends BaseEntity {
	
	@Column(name="role_name")
	private String roleName;
	
	@ManyToMany(mappedBy="authorities")
	private Set<User>  users= new HashSet<>();

}
