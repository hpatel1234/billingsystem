package com.assignment.business.service.impl;

import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.exception.ApplicationException;
import com.assignment.exception.BillNotExistsException;
import com.assignment.model.Bill;
import com.assignment.model.Product;
import com.assignment.test.config.AbstractBaseMockitoTest;
import com.assignment.web.request.UpdateBillRequest;

public class BillingBusinessServiceImplTest extends AbstractBaseMockitoTest {
	@InjectMocks
	private BillingBusinessServiceImpl billBusinessService;
	
	@Test(priority = 1)
	public void testGetNewBill() {
		Bill bill = billBusinessService.getNewBill();
		Assert.assertTrue(bill.getItems().isEmpty());
		Assert.assertTrue(bill.getBillNumber().equals(1L));
	}
	
	@Test(dataProvider = "validUpdateBillRequestProvider", priority = 2)
	public void testUpdateBill(UpdateBillRequest request) {
		Bill temp = billBusinessService.updateBill(request);
		Assert.assertNotNull(temp);
	}
	
	@Test(dataProvider = "validUpdateBillRequestProvider", priority = 3)
	public void testUpdateBillDuplicateProduct(UpdateBillRequest request) {
		Bill temp = billBusinessService.updateBill(request);
		Assert.assertNotNull(temp);
	}
	
	@Test(dataProvider = "validUpdateBillRequestProvider", expectedExceptions = ApplicationException.class,priority = 4)
	public void testUpdateBillWithInvalidProduct(UpdateBillRequest request) {
		request.getProduct().setBrand("");
		billBusinessService.updateBill(request);
	}
	@Test(dataProvider = "validUpdateBillRequestProvider", expectedExceptions = BillNotExistsException.class)
	public void testUpdateBillWithInvalidBillNumber(UpdateBillRequest request) {
		request.setBillNumber(null);
		billBusinessService.updateBill(request);
	}
	
	
	
	@DataProvider(name = "validUpdateBillRequestProvider")
	public Object[][] getValidUpdateBillRequest() {
		UpdateBillRequest request = new UpdateBillRequest();
		request.setBillNumber(1l);
		request.setCommit(false);
		Product prod = new Product();
		prod.setId("Id");
		prod.setBrand("Brand");
		prod.setCategory("category");
		prod.setName("name");
		prod.setPrice(new BigDecimal(1200));
		request.setProduct(prod);
		return new Object[][] { { request } };
	}
}
