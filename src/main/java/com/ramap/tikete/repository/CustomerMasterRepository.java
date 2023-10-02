package com.ramap.tikete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ramap.tikete.models.CustomerMaster;

public interface CustomerMasterRepository extends JpaRepository<CustomerMaster, Integer>{

	@Query(value = "SELECT * FROM customer_master WHERE customer_id_no=:customerIdNo and is_active=true",
			nativeQuery = true)
	CustomerMaster getCustomerByIdNo(String customerIdNo) ;
}
