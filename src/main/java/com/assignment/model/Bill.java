package com.assignment.model;

import java.math.BigDecimal;
import java.util.List;

public class Bill {
	private Long billNumber;
	private List<Item> items;
	private BigDecimal totalPrice = BigDecimal.ZERO;
	private BigDecimal totalTax = BigDecimal.ZERO;
	private BigDecimal actualCost = BigDecimal.ZERO;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getActualCost() {
		return actualCost;
	}

	public void setActualCost(BigDecimal totalCost) {
		this.actualCost = totalCost;
	}

	public Long getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(Long billnumber) {
		this.billNumber = billnumber;
	}

}
