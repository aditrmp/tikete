package com.ramap.tikete.controller.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramap.tikete.dto.request.VenueCreateData;
import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.service.events.VenueService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/venue")
public class VenueController {
	@Autowired
	VenueService venueSvc;

	@RequestMapping(value = "/add-venue", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addVenue(@RequestBody VenueCreateData req) throws Throwable {	
		log.info("Start /venue/add-venue username: {}", req.getAdmin());
		DataAddResponse resp = venueSvc.venueAdd(req);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/get-all-venue", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getVenueList() throws Throwable {	
		log.info("Start /venue/get-all-venue");
		DataAddResponse resp = venueSvc.venueList();
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
}
