package com.assignment.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.business.service.ProductBusinessService;
import com.assignment.exception.ProductNotExistException;
import com.assignment.model.Product;
import com.assignment.service.ProductService;

@Service("productBusinessService")
public class ProductBusinessServiceImpl implements ProductBusinessService {

	@Autowired
	private ProductService productService;
	
	@Override
	public Product getProduct(String productCode) {
		Product returnValue = productService.getProduct(productCode);
		if ( returnValue == null) {
			throw new ProductNotExistException();
		}
		return returnValue;
	}

}
