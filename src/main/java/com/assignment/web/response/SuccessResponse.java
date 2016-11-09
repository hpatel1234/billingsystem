package com.assignment.web.response;

import com.assignment.enums.Status;

public class SuccessResponse<T> extends BaseResponse<T> {
     public SuccessResponse() {
    	 setStatus(Status.SUCCESS);
     }
}
