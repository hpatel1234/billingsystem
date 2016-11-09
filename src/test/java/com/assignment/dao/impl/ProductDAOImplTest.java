package com.assignment.dao.impl;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.domainObjects.Product;
import com.assignment.test.config.AbstractBaseMockitoTest;

public class ProductDAOImplTest extends AbstractBaseMockitoTest {
	@InjectMocks
	private ProductDAOImpl productDao;
    @Mock
	private SessionFactory sessionFactory;
    @Mock
	private Session session;
    @Mock
	private Query query;

	
	@Test(dataProvider = "validProductProvider")
	public void testGetProduct(Product returnValue) throws Exception {
		String hql = "from Product where id=:id";
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.when(session.createQuery(hql)).thenReturn(query);
		Mockito.when(query.uniqueResult()).thenReturn(returnValue);
		Product temp = productDao.getProduct("1000");
		Assert.assertEquals(temp.getId(), "1000");
		Assert.assertEquals(temp.getBrand(), "testBrand");
		Assert.assertEquals(temp.getCategory(), "testCategory");
		Assert.assertEquals(temp.getName(), "testName");
		Assert.assertEquals(temp.getPrice(), new BigDecimal(1199.00d));
	}

	@Test
	public void testGetProductWithInvalidId() throws Exception {
		String hql = "from Product where id=:id";
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.when(session.createQuery(hql)).thenReturn(query);
		Mockito.when(query.uniqueResult()).thenReturn(null);
		Product temp = productDao.getProduct("1000");
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
