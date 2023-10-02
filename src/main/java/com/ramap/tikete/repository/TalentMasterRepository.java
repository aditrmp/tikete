package com.ramap.tikete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ramap.tikete.models.TalentMaster;

public interface TalentMasterRepository extends JpaRepository<TalentMaster, Integer>{
	@Query(value = "SELECT * FROM talent_master WHERE is_active=true",
			nativeQuery = true)
	List<TalentMaster> getAllTalent() ;
	
	@Query(value = "SELECT * FROM talent_master WHERE id=:id and is_active=true",
			nativeQuery = true)
	TalentMaster getTalentById(
			@Param("id") long id) ;
}
