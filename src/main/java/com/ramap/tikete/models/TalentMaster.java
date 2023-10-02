package com.ramap.tikete.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "talent_master")
@Data
public class TalentMaster extends DataTrail{

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "country", nullable = true)
	private String country;
	
	@Column(name = "type", nullable = true)
	private String type;

}
