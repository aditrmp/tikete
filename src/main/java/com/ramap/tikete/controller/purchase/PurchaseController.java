package com.ramap.tikete.controller.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramap.tikete.dto.request.PaymentUpdateReq;
import com.ramap.tikete.dto.request.PurchaseReq;
import com.ramap.tikete.dto.response.DataAddResponse;
import com.ramap.tikete.service.purchase.PurchaseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseSvc;

	@RequestMapping(value = "/save-order", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveOrder(@RequestBody PurchaseReq req) throws Throwable {	
		log.info("Start /purchase/save-order username: {}", req.getCustomerIdNo());
		DataAddResponse resp = purchaseSvc.saveOrder(req);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/get-payment-detail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getPaymentDetail(@RequestParam("paymentid") long paymentid) throws Throwable {	
		DataAddResponse resp = purchaseSvc.getPaymentDetails(paymentid);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/create-order", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createOrder(@RequestBody PurchaseReq req) throws Throwable {	
		log.info("Start /purchase/save-order username: {}", req.getCustomerIdNo());
		DataAddResponse resp = purchaseSvc.createOrder(req);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}

	@RequestMapping(value = "/update-payment-detail", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updatePaymentDetail(@RequestBody PaymentUpdateReq req) throws Throwable {	
		log.info("Start /purchase/update-payment-detail");
		DataAddResponse resp = purchaseSvc.updatePayment(req);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);	
	}
}
