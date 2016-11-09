package com.assignment.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.business.service.ProductBusinessService;
import com.assignment.model.Product;
import com.assignment.web.response.SuccessResponse;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductBusinessService productBusinessService;
	
	@ResponseBody
	@RequestMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public SuccessResponse<Product> getProduct(@PathVariable(value = "productId") String productCode) {
		Product data= productBusinessService.getProduct(productCode);
		SuccessResponse<Product> response = new SuccessResponse<Product>();
		response.setData(data);
		return response;
	}
}
