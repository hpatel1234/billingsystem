package com.assignment.exception;

public class ProductNotExistException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4224062832723221223L;

	public ProductNotExistException() {
		super("ERR-1003","Product doest not exist into system");
	}
}
