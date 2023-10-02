package com.ramap.tikete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ramap.tikete.models.VenueMaster;

public interface VenueMasterRepository  extends JpaRepository<VenueMaster, Integer>{
	@Query(value = "SELECT * FROM venue_master WHERE is_active=true",
			nativeQuery = true)
	List<VenueMaster> getAllVenue() ;

	@Query(value = "SELECT * FROM venue_master WHERE id=:id and is_active=true",
			nativeQuery = true)
	VenueMaster getVenueById(
			@Param("id") long id) ;
}
