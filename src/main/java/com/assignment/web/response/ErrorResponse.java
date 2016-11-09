package com.assignment.web.response;

import java.util.ArrayList;
import java.util.List;
import com.assignment.model.Error;
import com.assignment.enums.Status;
import com.assignment.exception.ApplicationException;

public class ErrorResponse extends BaseResponse {
	private List<Error> errors = new ArrayList<Error>();

	public ErrorResponse() {
		setStatus(Status.FAILURE);
	}
	
	public void addError(ApplicationException exception ) {
		errors.add(new Error(exception.getCode(), exception.getMessage()));
	}
	
	public List<Error> getErrors() {
		return errors;
	}
}
