package com.assignment.web.response;

import com.assignment.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public abstract class BaseResponse<T> {
	@JsonInclude(value = Include.NON_NULL)
	private T data;
	@JsonInclude(value = Include.NON_NULL)
	private Status status;
	@JsonInclude(value = Include.NON_NULL)
	private String message;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
