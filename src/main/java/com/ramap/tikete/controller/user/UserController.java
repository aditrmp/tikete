package com.ramap.tikete.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramap.tikete.dto.request.UserLoginDto;
import com.ramap.tikete.dto.response.LoginDetailDto;
import com.ramap.tikete.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userSvc;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> dataEntry(@RequestBody UserLoginDto req) throws Throwable {	
		log.info("Start /user/login username: {}", req.getUname());
		LoginDetailDto details = new LoginDetailDto();
		details = userSvc.checkLogin(req);
		
//		ResponseEntity resp = new ResponseEntity();
//		log.info("Start endpoint /loandataentry with userId : "+ req.getUserId());
//		if (!CustomValidations.isRequestNotNull(req)) 
//			return new ResponseEntity<>("Request is null", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(details, HttpStatus.OK);	
	}
}
