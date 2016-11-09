package com.assignment.business.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.assignment.business.service.BillingBusinessService;
import com.assignment.exception.ApplicationException;
import com.assignment.exception.BillNotExistsException;
import com.assignment.model.Bill;
import com.assignment.model.Item;
import com.assignment.model.Product;
import com.assignment.util.BillNumberGenerator;
import com.assignment.web.request.UpdateBillRequest;

@Service("billBusinessService")
public class BillingBusinessServiceImpl implements BillingBusinessService {
	private Map<Long, Bill> billsInProgress = new HashMap<Long, Bill>();

	@Override
	public Bill getNewBill() {
		Bill bill = new Bill();
		List<Item> items = new ArrayList<Item>();
		bill.setItems(items);
		bill.setBillNumber(BillNumberGenerator.getNextBill());
		billsInProgress.put(bill.getBillNumber(), bill);
		return bill;
	}

	@Override
	public Bill updateBill(UpdateBillRequest request) {
		validateRequest(request);
		Bill bill = billsInProgress.get(request.getBillNumber());
		List<Item> items = bill.getItems();
		boolean alreadyAdded = false;
		if (!items.isEmpty()) {
			for (Item temp : items) {
				if (temp.getName().equals(request.getProduct().getName())) {
					temp.setQuantity(temp.getQuantity() + 1);
					BigDecimal tax = calculateTax(request.getProduct());
					tax = tax.setScale(2, RoundingMode.HALF_UP);
					BigDecimal price = temp.getUnitPrice().multiply(new BigDecimal(1));
					price = price.setScale(2, RoundingMode.HALF_UP);
					temp.setTax(temp.getTax().add(tax).setScale(2, RoundingMode.HALF_UP));
					temp.setPrice(temp.getPrice().add(price).setScale(2, RoundingMode.HALF_UP));
					bill.setTotalPrice(bill.getTotalPrice().add(price).setScale(2, RoundingMode.HALF_UP));
					bill.setTotalTax(bill.getTotalTax().add(tax).setScale(2, RoundingMode.HALF_UP));
					bill.setActualCost(bill.getTotalPrice().add(bill.getTotalTax()).setScale(2, RoundingMode.HALF_UP));
					alreadyAdded = true;
				}
			}
		}
		if (!alreadyAdded) {
			Item convertedItem = convertProductToItem(request.getProduct());
			if(convertedItem != null) {
				bill.setTotalPrice(bill.getTotalPrice().add(convertedItem.getUnitPrice()).setScale(2, RoundingMode.HALF_UP));
				bill.setTotalTax(bill.getTotalTax().add(convertedItem.getTax()).setScale(2, RoundingMode.HALF_UP));
				bill.setActualCost(bill.getTotalPrice().add(bill.getTotalTax()).setScale(2, RoundingMode.HALF_UP));
				items.add(convertedItem);
			}
		}
		return bill;
	}

	/**
	 * @param request
	 */
	private void validateRequest(UpdateBillRequest request) {
		if (billsInProgress.get(request.getBillNumber()) == null) {
			throw new BillNotExistsException();
		}
		
		if (request.getProduct() == null || StringUtils.isEmpty(request.getProduct().getBrand()) 
				|| StringUtils.isEmpty(request.getProduct().getCategory())
				|| StringUtils.isEmpty(request.getProduct().getName())
				|| StringUtils.isEmpty(request.getProduct().getId())) {
			throw new ApplicationException("ERR-1005", "Product not present in update bill request");
		}
	}

	private Item convertProductToItem(Product product) {
		Item returnValue = null;
		if (product != null) {
			returnValue = new Item();
			returnValue.setName(product.getName());
			returnValue.setUnitPrice(product.getPrice().setScale(2, RoundingMode.HALF_UP));
			returnValue.setQuantity(1);
			returnValue.setTax(calculateTax(product).setScale(2, RoundingMode.HALF_UP));
			returnValue.setPrice(returnValue.getUnitPrice().setScale(2, RoundingMode.HALF_UP));
		}
		return returnValue;
	}

	/**
	 * @param product
	 * @return
	 */
	private BigDecimal calculateTax(Product product) {
		String multiplier = "0.00";
		if (product.getCategory().trim().equals("A")) {
			multiplier = "0.1";
		} else if (product.getCategory().trim().equals("B")) {
			multiplier = "0.2";
		}
		return product.getPrice().multiply(new BigDecimal(multiplier));
	}

}
