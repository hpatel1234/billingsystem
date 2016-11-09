package com.assignment.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Product {
	@JsonInclude(value = Include.NON_NULL)
	private String id;
	@JsonInclude(value = Include.NON_NULL)
	private String name;
	@JsonInclude(value = Include.NON_NULL)
	private String brand;
	@JsonInclude(value = Include.NON_NULL)
	private String category;
	@JsonInclude(value = Include.NON_NULL)
	private BigDecimal price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object object) {
		boolean equal = false;
		if (object != null && object instanceof Product) {
			Product other = (Product) object;
			if (other.id != null && other.id.equals(this.id)) {
				equal = true;
			}
		}

		return equal;
	}

	@Override
	public int hashCode() {
		int result = 2;
		int temp = id == null ? 0 : id.hashCode();
		result = 37 * result + temp;
		return result;
	}
}
