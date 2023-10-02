package com.ramap.tikete.models;
	
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer_master")
@Data
public class CustomerMaster extends DataTrail{
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "customer_name", nullable = true)
	private String customerName;
	
	@Column(name = "customer_id_no", nullable = false)
	private String cusotmerId_no;
	
	@Column(name = "customer_id_type", nullable = false)
	private String cusotmerIdType;
	
	@Column(name = "customer_dob", nullable = false)
	private String cusotmerDob;
	
	@Column(name = "customer_gender", nullable = false)
	private String cusotmerGender;
	
	@Column(name = "customer_address_city", nullable = false)
	private String cusotmerAddressCity;
	
//	@Column(name = "customer_", nullable = false)
//	private String cusotmer;
}
