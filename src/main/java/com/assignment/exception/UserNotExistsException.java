/**
 * UserNotExistsException.java
 * Feb 18, 2016
 */
package com.assignment.exception;

/**
 * <p>
 * User does not exist into application.
 * </p>
 * 
 * @author hemantp
 *
 */
public class UserNotExistsException extends ApplicationException {
	/**
	 * serialVersionUID - long
	 */
	private static final long serialVersionUID = -7298597395817847417L;

	public UserNotExistsException() {
		super("ERR-1001", "User could not be found in system.");
	}
}
