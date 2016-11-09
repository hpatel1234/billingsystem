/**
 * ApplicationException.java
 * Feb 18, 2016
 */
package com.assignment.exception;

/**
 * <p>
 * Base class for all exception that will be thrown from application.
 * </p>
 * 
 * @author hemantp
 * 
 */
public class ApplicationException extends RuntimeException {
    /**
     * serialVersionUID - long
     */
    private static final long serialVersionUID = -55811933807627247L;
    private String code = "ERR-1000";
    private String message = "Error occurred while processing request";
    
    public ApplicationException(Throwable throwable) {
    	super(throwable);
    }
    public ApplicationException(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
    	return message;
    }
}
