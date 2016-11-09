package com.assignment.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.business.service.ProductBusinessService;
import com.assignment.exception.ProductNotExistException;
import com.assignment.model.Product;
import com.assignment.test.config.BaseControllerTest;

public class ProductControllerTest extends BaseControllerTest {

	@InjectMocks
	private ProductController controller;

	@Mock
	private ProductBusinessService productBusinessService;

	@BeforeClass
	public void setUpBase() throws Exception {
		super.setUpMockMVC(controller);
	}

	@Test(dataProvider = "validProductProvider")
	public void testGetProduct(Product returnValue) throws Exception {
		Mockito.when(productBusinessService.getProduct(Mockito.anyString())).thenReturn(returnValue);
		this.mockMvc.perform(get("/products/1000").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("SUCCESS")).andExpect(jsonPath("$.data").exists());
		Mockito.verify(productBusinessService).getProduct(Mockito.anyString());
	}

	@Test
	public void testGetProductWithInvalidId()  throws Exception {
		Mockito.when(productBusinessService.getProduct(Mockito.anyString())).thenThrow(new ProductNotExistException());
		this.mockMvc.perform(get("/products/20000").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("FAILURE")).andExpect(jsonPath("$.data").doesNotExist());
		Mockito.verify(productBusinessService).getProduct(Mockito.anyString());
	}
	@DataProvider(name = "validProductProvider")
	public Object[][] getValidProduct() {
		Product temp = new Product();
		temp.setId("1000");
		return new Object[][] { { temp } };
	}
}
