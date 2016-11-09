package com.assignment.business.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.exception.ProductNotExistException;
import com.assignment.model.Product;
import com.assignment.service.ProductService;
import com.assignment.test.config.AbstractBaseMockitoTest;

public class ProductBusinessServiceImplTest extends AbstractBaseMockitoTest {

	@InjectMocks
	private  ProductBusinessServiceImpl productBusinessService;
	@Mock
	private ProductService productService;
	
	@Test(dataProvider = "validProductProvider")
	public void testGetProduct(Product returnValue) throws Exception {
		Mockito.when(productService.getProduct(Mockito.anyString())).thenReturn(returnValue);
		Product temp = productBusinessService.getProduct("1000");
		Mockito.verify(productService).getProduct(Mockito.anyString());
		Assert.assertEquals(temp, returnValue);
	}

	@Test(expectedExceptions = ProductNotExistException.class)
	public void testGetProductWithInvalidId()  throws Exception {
		Mockito.when(productService.getProduct(Mockito.anyString())).thenReturn(null);
		productBusinessService.getProduct("1000");
		Mockito.verify(productService).getProduct(Mockito.anyString());
	}
	
	@DataProvider(name = "validProductProvider")
	public Object[][] getValidProduct() {
		Product temp = new Product();
		temp.setId("1000");
		return new Object[][] { { temp } };
	}
}
