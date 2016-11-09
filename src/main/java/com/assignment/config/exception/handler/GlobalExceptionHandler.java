/**
 * GlobalExceptionHndler.java
 * Feb 17, 2016
 */
package com.assignment.config.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.assignment.exception.ApplicationException;
import com.assignment.web.response.ErrorResponse;

/**
 * <p>
 * Exception handler.
 * </p>
 * 
 * @author hemantp
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ErrorResponse handleGeneralException(final Exception ex) {
		ApplicationException wrapper = new ApplicationException(ex);
		return handleApplicationException(wrapper);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = ApplicationException.class)
	public ErrorResponse handleApplicationException(final ApplicationException ex) {
		LOGGER.error("Exception Logged", ex);
		ErrorResponse response = new ErrorResponse();
		response.addError(ex);
		return response;
	}
}
