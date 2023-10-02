package com.ramap.tikete.service.purchase;

import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ramap.tikete.config.kafka.KafkaSender;
import com.ramap.tikete.dto.request.PaymentUpdateReq;
import com.ramap.tikete.dto.request.PurchaseReq;
import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.dto.response.PaymentDetailsRes;
import com.ramap.tikete.models.CustomerMaster;
import com.ramap.tikete.models.CustomerTicketMap;
import com.ramap.tikete.models.CustomerTrx;
import com.ramap.tikete.models.EventMaster;
import com.ramap.tikete.repository.CustomerMasterRepository;
import com.ramap.tikete.repository.CustomerTicketMapRepository;
import com.ramap.tikete.repository.CustomerTrxRepository;
import com.ramap.tikete.repository.EventMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PurchaseService {
	@Autowired
	CustomerTicketMapRepository ctmapRepo;
	
	@Autowired
	CustomerTrxRepository ctrxRepo;

	@Autowired
	CustomerMasterRepository csmasterRepo;

	@Autowired
	EventMasterRepository eventMasterRepo;
	
	Date datenow = new Date();
	Timestamp ts = new Timestamp(datenow.getTime());
	
	@Autowired
	private KafkaSender kafkaSender;
	
	@Transactional
	public DataAddResponse saveOrder(PurchaseReq req) {
		CustomerTicketMap custTicketMap = new CustomerTicketMap();
		CustomerTrx trx = new CustomerTrx();
		CustomerMaster csMaster = new CustomerMaster();
		DataAddResponse result = new DataAddResponse();
		EventMaster eventMaster = new EventMaster();
		Integer countRemaining = 0;
		long paymentAmount = 0;
		
		try {
			csMaster = csmasterRepo.getCustomerByIdNo(req.getCustomerIdNo());
			eventMaster = eventMasterRepo.getEventById(req.getEventIdNo());
			countRemaining = eventMaster.getCount() - req.getReservationCount();
			paymentAmount = eventMaster.getPrice() * req.getReservationCount();
			if(countRemaining > 0) {
				trx.setIdCustomer(req.getCustomerIdNo());
				trx.setPaymentDetails(null);
				trx.setPaymentStatus(null);
				trx.setPaymentAmount(paymentAmount);
				trx.setActive(true);
				trx.setCreatedDate(datenow);
				ctrxRepo.saveAndFlush(trx);
				
				custTicketMap.setIdEvent(req.getEventIdNo());
				custTicketMap.setIdCustomer(req.getCustomerIdNo());
				custTicketMap.setTicketCount(req.getReservationCount());
				custTicketMap.setIdPayment(trx.getId());
				custTicketMap.setActive(true);
				custTicketMap.setCreatedDate(ts);
				ctmapRepo.saveAndFlush(custTicketMap);
				
				eventMaster.setCount(countRemaining);
				eventMaster.setUpdatedBy("admin");
				eventMaster.setUpdatedDate(ts);
				eventMasterRepo.saveAndFlush(eventMaster);
				
				result.setMessage("Success save trx and maptrx");
				result.setStatus("OK");		
			}else {
				result.setMessage("Ticket is empty");
				result.setStatus("empty");	
			}
		}catch (Exception e) {
			e.printStackTrace();
			
			result.setMessage("Failed save trx and maptrx");
			result.setStatus("Failed");			
		}
		return result;
	}
	
	public DataAddResponse getPaymentDetails(long id) {
		CustomerTicketMap custTicketMap = new CustomerTicketMap();
		CustomerTrx trx = null;
		DataAddResponse result = new DataAddResponse();
		PaymentDetailsRes paymentDetail = null;

		try {
			trx = ctrxRepo.getTrxById(id);
			custTicketMap = ctmapRepo.getCustomerTicketByPaymentId(trx.getId());
			if(trx!=null) {
				paymentDetail.setPaymentStatus(trx.getPaymentStatus());
				paymentDetail.setEventId(custTicketMap.getIdEvent());
				paymentDetail.setUserid(trx.getIdCustomer());
				
				result.setData(paymentDetail);
				result.setMessage("Success get payment detail");
				result.setStatus("OK");		
			}else {
				result.setMessage("Payment details not found.");
				result.setStatus("OK");		
			}
		}catch (Exception e) {
			e.printStackTrace();
			
			result.setMessage("Failed save payment");
			result.setStatus("Failed");		
		}
		return null;
	}
	
	public DataAddResponse updatePayment(PaymentUpdateReq req) {
		CustomerTicketMap custTicketMap = new CustomerTicketMap();
		CustomerTrx trx = null;
		DataAddResponse result = new DataAddResponse();

		try {
			trx = ctrxRepo.getTrxById(req.getPaymentId());
			if(trx!=null) {
				trx.setPaymentAmount(req.getPaymentAmount());
				trx.setPaymentDetails(req.getDetails());
				trx.setPaymentStatus(req.getPaymentStatus());
				ctrxRepo.saveAndFlush(trx);
				
				result.setMessage("Success save payment");
				result.setStatus("OK");		
			}else {
				result.setMessage("Payment details not found.");
				result.setStatus("OK");		
			}
		}catch (Exception e) {
			e.printStackTrace();
			
			result.setMessage("Failed save payment");
			result.setStatus("Failed");		
		}
		return null;
	}
	
	public DataAddResponse createOrder(PurchaseReq req) {
		DataAddResponse result = new DataAddResponse();

		kafkaSender.sendMessage("purchase-queue", req);
		
		result.setMessage("Order queue created");
		result.setStatus("OK");		
		return result;
	}

	@KafkaListener(topics = "purchase-queue", groupId = "myGroup")
	void listener(PurchaseReq message) throws URISyntaxException {
		log.info("Listener CREATE ORDER "	 + message);
		System.out.println(message);
		DataAddResponse saveOrder = saveOrder(message);
		
		log.info("Save order response "+saveOrder);
	}
}
