package com.ramap.tikete.dto.request;

import lombok.Data;

@Data
public class PurchaseReq {
	public String customerIdNo;
	public String eventIdNo;
	public Integer reservationCount;
	public String paymentId;
}
