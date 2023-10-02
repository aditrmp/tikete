package com.ramap.tikete.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@MappedSuperclass
@Data
public class DataTrail {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = true)
	private Date createdDate ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = true)
	private Date updatedDate;
	
	@Column(name = "created_by", nullable = true)
	private String createdBy;
	
	@Column(name = "updated_by", nullable = true)
	private String updatedBy;
	
	@Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
	private boolean isActive;
	
	
	public DataTrail() {
		Date date = new Date();
//		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		if(createdDate == null) {
			createdDate = date;
		}else {
			updatedDate = date;
		}
	}
}
