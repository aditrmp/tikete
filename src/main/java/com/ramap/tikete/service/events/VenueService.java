package com.ramap.tikete.service.events;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramap.tikete.dto.request.VenueCreateData;
import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.dto.response.VenueDto;
import com.ramap.tikete.models.VenueMaster;
import com.ramap.tikete.repository.VenueMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VenueService {
	@Autowired
	VenueMasterRepository venuemasterRepo;
	
	Date datenow = new Date();
	Timestamp ts = new Timestamp(datenow.getTime());
	
	@Transactional
	public DataAddResponse venueAdd(VenueCreateData req) {
		DataAddResponse result = new DataAddResponse();
		try {
			VenueMaster data = new VenueMaster();
			
			data.setName(req.getName());
			data.setCountry(req.getCountry());
			data.setCity(req.getCity());
			data.setLocation(req.getLocation());
			data.setCreatedBy(req.getAdmin());
			data.setCreatedDate(ts);
			data.setActive(true);
			
			venuemasterRepo.saveAndFlush(data);
			
			result.setMessage("Success save venue.");
			result.setStatus("OK");			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			result.setMessage("Failed save venue");
			result.setStatus("Failed");			
		}
		return result;
	}
	
	public DataAddResponse venueList() {
		VenueDto venueDto = new VenueDto();
		DataAddResponse result = new DataAddResponse();
		List<VenueMaster> venueList = new ArrayList<VenueMaster>() ;		
		List<VenueDto> venueDtoList = new ArrayList<VenueDto>() ;
		
		try {
			venueList = venuemasterRepo.getAllVenue();
			
			for(VenueMaster data: venueList) {
				venueDto = new VenueDto();
				venueDto.setName(data.getName());
				venueDto.setCity(data.getCity());
				venueDto.setLocation(data.getLocation());
				venueDto.setCountry(data.getCountry());
				
				venueDtoList.add(venueDto);
			}
			
			result.setData(venueDtoList);
			result.setMessage("");
			result.setStatus("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setMessage("ERROR get venue");
			result.setStatus("ERROR");
		}
		
		return result;
	}

	public DataAddResponse getVenue(long id) {
		VenueDto venueDto = new VenueDto();
		DataAddResponse result = new DataAddResponse();
		VenueMaster venueMaster = new VenueMaster();
//		List<VenueMaster> venueList = new ArrayList<VenueMaster>() ;		
//		List<VenueDto> venueDtoList = new ArrayList<VenueDto>() ;
		
		try {
			venueMaster = venuemasterRepo.getVenueById(id);
			venueDto.setName(venueMaster.getName());
			venueDto.setCity(venueMaster.getCity());
			venueDto.setLocation(venueMaster.getLocation());
			venueDto.setCountry(venueMaster.getCountry());
			
			
			result.setData(venueDto);
			result.setMessage("");
			result.setStatus("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setMessage("ERROR get venue");
			result.setStatus("ERROR");
		}
		return result;
	}
	
	public VenueDto getVenueDto(long id) {
		VenueDto venueDto = new VenueDto();
		VenueMaster venueMaster = new VenueMaster();
		try {
			venueMaster = venuemasterRepo.getVenueById(id);
			venueDto.setName(venueMaster.getName());
			venueDto.setCity(venueMaster.getCity());
			venueDto.setLocation(venueMaster.getLocation());
			venueDto.setCountry(venueMaster.getCountry());
			
			
		}catch (Exception e) {
			e.printStackTrace();
			venueDto = null;
		}
		return venueDto;
	}
}
