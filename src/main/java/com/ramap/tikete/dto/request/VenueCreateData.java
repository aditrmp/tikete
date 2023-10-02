package com.ramap.tikete.dto.request;

import lombok.Data;

@Data
public class VenueCreateData {	
	private String name;
	private String city;
	private String country;
	private String location;
	private String admin;
}
