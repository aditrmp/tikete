package com.ramap.tikete.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "user_master")
@Data
public class UserMaster extends DataTrail{

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "user_name", nullable = true)
	private String username;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "password", nullable = true)
	private String password;
	
	@Column(name = "login_trial_count", nullable = true)
	private int loginTrialCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login", nullable = true)
	private Date lastLogin;
	
	@Column(name = "customer_id", nullable = true)
	private long customerId;
}
