package com.ramap.tikete.service.events;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.dto.response.TalentDto;
import com.ramap.tikete.models.TalentMaster;
import com.ramap.tikete.repository.TalentMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TalentService {
	@Autowired
	TalentMasterRepository talentMasterRepo;

	Date datenow = new Date();
	Timestamp ts = new Timestamp(datenow.getTime());
	
	@Transactional
	public DataAddResponse talentAdd(TalentDto req) {
		DataAddResponse result = new DataAddResponse();
		try {
			TalentMaster data = new TalentMaster();
			
			data.setName(req.getName());
			data.setCountry(req.getCountry());
			data.setCreatedBy("admin");
			data.setType(req.getType());
			data.setCreatedDate(ts);
			data.setActive(true);
			
			talentMasterRepo.saveAndFlush(data);
			
			result.setMessage("Success save talent.");
			result.setStatus("OK");			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			result.setMessage("Failed save talent");
			result.setStatus("Failed");			
		}
		return result;
	}
	
	public DataAddResponse getTalentData() {
		DataAddResponse result = new DataAddResponse();
		List<TalentMaster> talentMasters = new ArrayList<TalentMaster>();
		TalentDto talentDto = new TalentDto();
		List<TalentDto> talentList = new ArrayList<TalentDto>();
		
		try {
			talentMasters = talentMasterRepo.getAllTalent();
			
			for(TalentMaster data : talentMasters) {
				talentDto = new TalentDto();
				talentDto.setCountry(data.getCountry());
				talentDto.setName(data.getName());
				talentDto.setType(data.getType());
				talentDto.setAdmin("");
				
				talentList.add(talentDto);
			}
			
			result.setData(talentList);
			result.setMessage("");
			result.setStatus("OK");
		}catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setMessage("ERROR get talent");
			result.setStatus("ERROR");
		}
		
		return result;
	}

	public TalentDto getTalentDataDto(long id) {
		TalentMaster talentMaster = new TalentMaster();
		TalentDto talentDto = new TalentDto();
		
		try {
			talentMaster = talentMasterRepo.getTalentById(id);
			talentDto.setCountry(talentMaster.getCountry());
			talentDto.setName(talentMaster.getName());
			talentDto.setType(talentMaster.getType());
				
		}catch (Exception e) {
			e.printStackTrace();
			talentDto = null;
		}
		
		return talentDto;
	}
}
