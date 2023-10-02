package com.ramap.tikete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ramap.tikete.models.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Integer>{
	@Query(value = "SELECT * FROM user_master WHERE user_name=:uname and password=:pass and is_active=:isactive",
			nativeQuery = true)
	UserMaster checkLogin(
			@Param("uname") String uname,
			@Param("pass") String pass,
			@Param("isactive") boolean isactive
	) ;
}
