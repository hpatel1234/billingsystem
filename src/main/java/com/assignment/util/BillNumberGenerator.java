package com.assignment.util;

public abstract class BillNumberGenerator {
	private static long counter = 1l;
	public static synchronized long  getNextBill() {
		return counter++;
	}
}
