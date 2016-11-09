package com.assignment.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.business.service.BillingBusinessService;
import com.assignment.exception.BillNotExistsException;
import com.assignment.model.Bill;
import com.assignment.model.Product;
import com.assignment.test.config.BaseControllerTest;
import com.assignment.web.request.UpdateBillRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BillingControllerTest extends BaseControllerTest {
	private static ObjectMapper mapper = new ObjectMapper();
	@InjectMocks
	private BillingController controller;
	@Mock
	private BillingBusinessService billBusinessService;
	
	@BeforeClass
	public void setUpBase() throws Exception {
		super.setUpMockMVC(controller);
	}
	
	@Test(dataProvider = "validBillProvider")
	public void testGenerateBill(Bill returnValue) throws Exception {
		Mockito.when(billBusinessService.getNewBill()).thenReturn(returnValue);
		this.mockMvc.perform(post("/bills/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("SUCCESS")).andExpect(jsonPath("$.data").exists()).andExpect(jsonPath("$.data.billNumber").value(100000));
		Mockito.verify(billBusinessService).getNewBill();
	}
	
	@Test(dataProvider = "validUpdateBillRequestProvider")
	public void testUpdateBill(UpdateBillRequest request) throws Exception {
		Mockito.when(billBusinessService.updateBill(Mockito.any(UpdateBillRequest.class))).thenReturn(new Bill());
		this.mockMvc.perform(post("/bills/1").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request))).andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("SUCCESS")).andExpect(jsonPath("$.data").exists());
		Mockito.verify(billBusinessService).updateBill(Mockito.any(UpdateBillRequest.class));
	}
	
	@Test(dataProvider = "validUpdateBillRequestProvider")
	public void testUpdateBillWithInvalidBillNumber(UpdateBillRequest request)  throws Exception {
		Mockito.when(billBusinessService.updateBill(Mockito.any(UpdateBillRequest.class))).thenThrow(new BillNotExistsException());
		this.mockMvc.perform(post("/bills/2").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request))).andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("FAILURE")).andExpect(jsonPath("$.data").doesNotExist());
		Mockito.verify(billBusinessService).updateBill(Mockito.any(UpdateBillRequest.class));
	}
	
	@Test
	public void testUpdateBillWithNullRequest()  throws Exception {
		this.mockMvc.perform(post("/bills/2").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError())
		.andExpect(jsonPath("$.status").value("FAILURE")).andExpect(jsonPath("$.data").doesNotExist());
	}
	
	@DataProvider(name = "validBillProvider")
	public Object[][] getValidProduct() {
		Bill temp = new Bill();
		temp.setBillNumber(100000l);
		return new Object[][] { { temp } };
	}
	@DataProvider(name = "validUpdateBillRequestProvider")
	public Object[][] getValidUpdateBillRequest() {
		UpdateBillRequest request = new UpdateBillRequest();
		request.setBillNumber(1l);
		request.setCommit(false);
		request.setProduct(new Product());
		return new Object[][] { { request } };
	}
	
}
