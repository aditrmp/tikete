package com.ramap.tikete.service.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramap.tikete.dto.request.UserLoginDto;
import com.ramap.tikete.dto.response.LoginDetailDto;
import com.ramap.tikete.models.UserMaster;
import com.ramap.tikete.repository.UserMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	@Autowired
	private UserMasterRepository userRepo;
	
	Date datenow = new Date();
	Timestamp ts = new Timestamp(datenow.getTime());
	
	public LoginDetailDto checkLogin(UserLoginDto req) {
		log.info("Start checkLogin username: {}", req.getUname());
		try {
			UserMaster user = userRepo.checkLogin(req.getUname(), req.getPass(), true);
			LoginDetailDto details = new LoginDetailDto();
			
			if(user != null) {
				details.setStatusLogin(true);
				details.setUname(user.getUsername());
				
				user.setLastLogin(ts);
				user.setUpdatedDate(ts);
				user.setActive(true);
				userRepo.saveAndFlush(user);
				return details;
			}
			
			return null;
		}catch (Exception e) {
			// TODO: handle exception
//			log.error("ERROR : {}", e.getStackTrace());
			e.printStackTrace();
		}
		return null;
	}
}
