/**
 * InvalidCredentialsException.java
 * Feb 18, 2016
 */
package com.assignment.exception;

/**
 * <p>
 * Credentials provided are not valid.
 * </p>
 * @author hemantp
 *
 */
public class InvalidCredentialsException extends ApplicationException {

    /**
     * serialVersionUID - long
     */
    private static final long serialVersionUID = -6866573031445568414L;
    public InvalidCredentialsException() {
       super("ERR-1002", "Invalid credentials.");
    }
}
