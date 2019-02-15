package com.sandeeprm.oms.inventoryservice.controllers.common;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	private String message;

	public static ApiError newInstance(HttpStatus httpStatus, String message) {
		ApiError apiError = new ApiError();
		apiError.setHttpStatus(httpStatus);
		apiError.setMessage(message);
		return apiError;
	}
}
