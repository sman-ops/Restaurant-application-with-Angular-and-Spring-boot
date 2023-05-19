package com.spring.restaurant.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User extends BaseEntity {
	
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="active")
	private int active;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(
			name="user_role",
			joinColumns= @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="authorities_id")
			
			)
	private List<Authorities> authorities =new ArrayList();
	
	

}
