package com.ramap.tikete.dto.request;

import lombok.Data;

@Data
public class PaymentUpdateReq {
	public long paymentId;
	public long paymentAmount;
	public String details;
	public String paymentStatus;
}
