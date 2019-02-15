package com.sandeeprm.oms.orderservice.controllers.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sandeeprm.oms.orderservice.services.exceptions.OrderServiceException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OrderServiceException.class)
	protected ResponseEntity<ApiError> handleTodoServiceException(OrderServiceException ose) {

		ApiError error = ApiError.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, ose.getMessage());

		return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
