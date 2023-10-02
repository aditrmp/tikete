package com.ramap.tikete.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class EventDtoReq {
	public String eventCode;
	public String eventName;
	public String details;
	public long price;
	public Integer capacity;
	public Date eventDate;
	public long venueId;
	public String artistId;
	public Integer count;
}
