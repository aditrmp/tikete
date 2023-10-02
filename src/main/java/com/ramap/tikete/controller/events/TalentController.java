package com.ramap.tikete.controller.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.dto.response.TalentDto;
import com.ramap.tikete.service.events.TalentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/talent")
public class TalentController {
	@Autowired
	TalentService talentSvc;

	@RequestMapping(value = "/add-talent", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addVenue(@RequestBody TalentDto req) throws Throwable {	
		log.info("Start /venue/add-venue username: {}", req.getAdmin());
		DataAddResponse resp = talentSvc.talentAdd(req);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/get-all-talent", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getVenueList() throws Throwable {	
		log.info("Start /venue/get-all-talent");
		DataAddResponse resp = talentSvc.getTalentData();
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
}
