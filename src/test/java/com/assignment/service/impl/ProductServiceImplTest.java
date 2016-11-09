package com.assignment.service.impl;

import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.dao.ProductDAO;
import com.assignment.domainObjects.Product;
import com.assignment.test.config.AbstractBaseMockitoTest;

public class ProductServiceImplTest extends AbstractBaseMockitoTest {
	@InjectMocks
	private ProductServiceImpl productService;
	@Mock
	private ProductDAO productDao;
	@Test(dataProvider = "validProductProvider")
	public void testGetProduct(Product returnValue) throws Exception {
		Mockito.when(productDao.getProduct(Mockito.anyString())).thenReturn(returnValue);
		com.assignment.model.Product temp = productService.getProduct("1000");
		Mockito.verify(productDao).getProduct(Mockito.anyString());
		Assert.assertEquals(temp.getId(), "1000");
		Assert.assertEquals(temp.getBrand(), "testBrand");
		Assert.assertEquals(temp.getCategory(), "testCategory");
		Assert.assertEquals(temp.getName(), "testName");
		Assert.assertEquals(temp.getPrice(), new BigDecimal(1199.00d));
	}

	@Test
	public void testGetProductWithInvalidId()  throws Exception {
		Mockito.when(productDao.getProduct(Mockito.anyString())).thenReturn(null);
		com.assignment.model.Product temp = productService.getProduct("1000");
		Mockito.verify(productDao).getProduct(Mockito.anyString());
		Assert.assertNull(temp);
	}
	
	@DataProvider(name = "validProductProvider")
	public Object[][] getValidProduct() {
		Product temp = new Product();
		temp.setId("1000");
		temp.setBrand("testBrand");
		temp.setCategory("testCategory");
		temp.setName("testName");
		temp.setPrice(new BigDecimal(1199.00d));
		return new Object[][] { { temp } };
	}
}
