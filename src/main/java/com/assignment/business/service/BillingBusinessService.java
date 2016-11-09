package com.assignment.business.service;

import com.assignment.model.Bill;
import com.assignment.web.request.UpdateBillRequest;

public interface BillingBusinessService {
	Bill getNewBill();
	Bill updateBill(UpdateBillRequest request);
}
