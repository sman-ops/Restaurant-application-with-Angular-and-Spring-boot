package com.spring.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_code")
public class Code extends BaseEntity {
	
	@Lob
	@Column(name="code")
	private String Code;
	
	@OneToOne(mappedBy = "code")
	private User user;

}
