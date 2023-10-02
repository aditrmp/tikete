package com.ramap.tikete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ramap.tikete.models.EventMaster;

public interface EventMasterRepository extends JpaRepository<EventMaster, Integer>{
	@Query(value = "SELECT * FROM event_master WHERE is_active=true",
			nativeQuery = true)
	List<EventMaster> getAllEvent() ;
	
	@Query(value = "SELECT * FROM event_master WHERE event_code=:eventCode and is_active=true",
			nativeQuery = true)
	EventMaster getEventById(String eventCode) ;
}
