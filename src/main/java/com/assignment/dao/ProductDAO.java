package com.assignment.dao;

import com.assignment.domainObjects.Product;

public interface ProductDAO {
	Product getProduct(String productCode);
}
