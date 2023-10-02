package com.ramap.tikete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ramap.tikete.models.CustomerTicketMap;

public interface CustomerTicketMapRepository extends JpaRepository<CustomerTicketMap, Integer>{

	@Query(value = "SELECT * FROM customer_ticket_map WHERE id_payment=:payment_id and is_active=true",
			nativeQuery = true)
	CustomerTicketMap getCustomerTicketByPaymentId(long payment_id) ;
}
