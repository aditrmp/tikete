package com.ramap.tikete.controller.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramap.tikete.dto.request.EventDtoReq;
import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.service.events.EventsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	EventsService eventSvc;
	
	@RequestMapping(value = "/add-event", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addEvent(@RequestBody EventDtoReq req) throws Throwable {	
		log.info("Start /event/add-event");
		DataAddResponse details = new DataAddResponse();
		details = eventSvc.eventAdd(req);
		
		return new ResponseEntity<>(details, HttpStatus.OK);	
	}

	@RequestMapping(value = "/get-all-event", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEventList() throws Throwable {	
		log.info("Start /event/get-all-event");
		DataAddResponse resp = eventSvc.getAllEventData();
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}

	@RequestMapping(value = "/get-event", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getevent(@RequestParam("eventCode") String eventCode) throws Throwable {	
		log.info("Start /event/get-event "+ eventCode);
		DataAddResponse resp = eventSvc.getEventData(eventCode);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
}
