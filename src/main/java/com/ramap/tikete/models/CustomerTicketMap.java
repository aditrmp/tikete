package com.ramap.tikete.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "customer_ticket_map")
@Data
public class CustomerTicketMap extends DataTrail{

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "id_cust", nullable = true)
	private String idCustomer;
	
	@Column(name = "id_event", nullable = true)
	private String idEvent;
	
	@Column(name = "id_payment", nullable = true)
	private long idPayment;
	
	@Column(name = "ticket_count", nullable = true)
	private Integer ticketCount;
	
	@Column(name = "ticket_validity", nullable = true)
	private boolean ticketValidity;

}
