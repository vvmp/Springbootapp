package com.driversapp.driversapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", insertable = false, updatable = false)
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middlename;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phonenumber")
	private String phonenumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "height")
	private String height;
	
	@Column(name = "weight")
	private String weight;
	
	

}
