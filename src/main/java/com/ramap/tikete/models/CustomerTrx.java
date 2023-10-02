package com.ramap.tikete.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer_trx")
@Data
public class CustomerTrx extends DataTrail{

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "payment_refference", nullable = true)
	private String paymentRefference;
	
	@Column(name = "id_cust", nullable = true)
	private String idCustomer;
	
	@Column(name = "payment_details", nullable = true)
	private String paymentDetails;
	
	@Column(name = "status_payment", nullable = true)
	private String paymentStatus;
	
	@Column(name = "payment_amount", nullable = true)
	private long paymentAmount;
}
