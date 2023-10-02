package com.ramap.tikete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ramap.tikete.models.CustomerTrx;

public interface CustomerTrxRepository extends JpaRepository<CustomerTrx, Integer>{
	@Query(value = "SELECT * FROM customer_trx WHERE id=:id and is_active=true",
			nativeQuery = true)
	CustomerTrx getTrxById(long id) ;
}
