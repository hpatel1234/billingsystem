package com.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dao.ProductDAO;
import com.assignment.model.Product;
import com.assignment.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDao;
	@Override
	public Product getProduct(String productCode) {
		com.assignment.domainObjects.Product entity = productDao.getProduct(productCode);
		return convertToModel(entity);
	}
	private Product convertToModel(com.assignment.domainObjects.Product entity) {
		Product returnValue = null;
		if(entity != null) {
			returnValue = new Product();
			returnValue.setId(entity.getId());
			returnValue.setBrand(entity.getBrand());
			returnValue.setCategory(entity.getCategory());
			returnValue.setName(entity.getName());
			returnValue.setPrice(entity.getPrice());
		}
		return returnValue;
	}

}
