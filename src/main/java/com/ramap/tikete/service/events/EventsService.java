package com.ramap.tikete.service.events;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramap.tikete.dto.request.EventDtoReq;
import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.dto.response.EventDtoRes;
import com.ramap.tikete.dto.response.TalentDto;
import com.ramap.tikete.dto.response.VenueDto;
import com.ramap.tikete.models.EventMaster;
import com.ramap.tikete.repository.EventMasterRepository;
import com.ramap.tikete.repository.TalentMasterRepository;
import com.ramap.tikete.repository.VenueMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventsService {
	@Autowired
	EventMasterRepository eventMasterRepo;

	@Autowired
	VenueMasterRepository venueMasterRepo;

	@Autowired
	TalentMasterRepository talentMasterRepo;

	@Autowired
	VenueService venueSvc;

	@Autowired
	TalentService talentSvc;

	Date datenow = new Date();
	Timestamp ts = new Timestamp(datenow.getTime());

	@Transactional
	public DataAddResponse eventAdd(EventDtoReq req) {
		DataAddResponse result = new DataAddResponse();
		try {
			EventMaster data = new EventMaster();

			data.setEventCode(req.getEventCode());
			data.setEventName(req.getEventName());
			data.setDetails(req.getDetails());
			data.setPrice(req.getPrice());
			data.setCapacity(req.getCapacity());
			data.setEventDate(req.getEventDate());
			data.setVenueId(req.getVenueId());
			data.setArtistId(req.getArtistId());
			data.setCount(req.getCount());
			data.setCreatedDate(ts);
			data.setCreatedBy("admin");
			data.setActive(true);

			eventMasterRepo.saveAndFlush(data);

			result.setMessage("Success save event.");
			result.setStatus("OK");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			result.setMessage("Failed save event");
			result.setStatus("Failed");
		}
		return result;
	}

	public DataAddResponse getAllEventData() {
		DataAddResponse result = new DataAddResponse();
		List<EventMaster> eventMaster = new ArrayList<EventMaster>();
		EventDtoRes eventDto = new EventDtoRes();
		List<EventDtoRes> eventList = new ArrayList<EventDtoRes>();
		VenueDto venue = new VenueDto();
		TalentDto talent = new TalentDto();
//		String talentListStr = "1;123";
//		String[] talentArr = talentListStr.split(";");
//		List<String> taletList = new ArrayList<String>();

		try {
			eventMaster = eventMasterRepo.getAllEvent();
			for (EventMaster data : eventMaster) {
				eventDto = new EventDtoRes();

				venue = venueSvc.getVenueDto(data.getVenueId());
				talent = talentSvc.getTalentDataDto(Long.valueOf(data.getArtistId()));

				eventDto.setEventCode(data.getEventCode());
				eventDto.setEventName(data.getEventName());
				eventDto.setDetails(data.getDetails());
				eventDto.setPrice(data.getPrice());
				eventDto.setCapacity(data.getCapacity());
				eventDto.setEventDate(data.getEventDate());
				eventDto.setCount(data.getCount());
				eventDto.setVenue(venue.getName());
				eventDto.setArtist(talent.getName());

				eventList.add(eventDto);
			}

			result.setData(eventList);
			result.setMessage("");
			result.setStatus("OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setMessage("ERROR get event");
			result.setStatus("ERROR");
		}
		return result;
	}

	public DataAddResponse getEventData(String eventCode) {
		DataAddResponse result = new DataAddResponse();
		EventMaster eventMaster = new EventMaster();
		EventDtoRes eventDto = new EventDtoRes();
		VenueDto venue = new VenueDto();
		TalentDto talent = new TalentDto();

		try {
			eventMaster = eventMasterRepo.getEventById(eventCode);
			venue = venueSvc.getVenueDto(eventMaster.getVenueId());
			talent = talentSvc.getTalentDataDto(Long.valueOf(eventMaster.getArtistId()));

			eventDto.setEventCode(eventMaster.getEventCode());
			eventDto.setEventName(eventMaster.getEventName());
			eventDto.setDetails(eventMaster.getDetails());
			eventDto.setPrice(eventMaster.getPrice());
			eventDto.setCapacity(eventMaster.getCapacity());
			eventDto.setEventDate(eventMaster.getEventDate());
			eventDto.setCount(eventMaster.getCount());
			eventDto.setVenue(venue.getName());
			eventDto.setArtist(talent.getName());

			result.setData(eventDto);
			result.setMessage("");
			result.setStatus("OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setMessage("ERROR get event");
			result.setStatus("ERROR");
		}
		return result;
	}
}
