package com.assignment.exception;

public class BillNotExistsException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5711182986244510257L;

	public BillNotExistsException() {
		super("ERR-1004", "Bill doest not exist into system");
	}

}
