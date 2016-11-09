package com.assignment.web.request;

import com.assignment.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBillRequest {
	private Long billNumber;
	private boolean commit;
	private Product product;
	public Long getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}
	public boolean isCommit() {
		return commit;
	}
	public void setCommit(boolean commit) {
		this.commit = commit;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
