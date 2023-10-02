package com.ramap.tikete.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "event_master")
@Data
public class EventMaster extends DataTrail{

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "event_code", nullable = true)
	private String eventCode;
	
	@Column(name = "event_name", nullable = true)
	private String eventName;
	
	@Column(name = "details", nullable = true)
	private String details;
	
	@Column(name = "price", nullable = true)
	private long price;
	
	@Column(name = "capacity", nullable = true)
	private Integer capacity;
	
	@Column(name = "event_date", nullable = true)
	private Date eventDate;
	
	@Column(name = "venue_id", nullable = true)
	private long venueId;
	
	@Column(name = "artist_id", nullable = true)
	private String artistId;
	
	@Column(name = "ticket_count", nullable = true)
	private Integer count;
}
