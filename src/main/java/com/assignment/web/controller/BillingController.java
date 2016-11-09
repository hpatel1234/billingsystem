package com.assignment.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.business.service.BillingBusinessService;
import com.assignment.model.Bill;
import com.assignment.web.request.UpdateBillRequest;
import com.assignment.web.response.SuccessResponse;

@Controller
@RequestMapping("/bills")
public class BillingController {
	@Autowired
	private BillingBusinessService billBusinessService;

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SuccessResponse<Bill> generateBill() {
		SuccessResponse<Bill> response = new SuccessResponse<Bill>();
		response.setData(billBusinessService.getNewBill());
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/{billnumber}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public SuccessResponse<Bill> updateBill(@RequestBody UpdateBillRequest request) {
		SuccessResponse<Bill> response = new SuccessResponse<Bill>();
		response.setData(billBusinessService.updateBill(request));
		return response;
	}

}
