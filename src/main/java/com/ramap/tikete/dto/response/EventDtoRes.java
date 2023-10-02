package com.ramap.tikete.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class EventDtoRes {
	public String eventCode;
	public String eventName;
	public String details;
	public long price;
	public Integer capacity;
	public Date eventDate;
	public String venue;
	public String artist;
	public Integer count;
}
