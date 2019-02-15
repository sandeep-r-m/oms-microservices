package com.sandeeprm.oms.inventoryservice.controllers.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sandeeprm.oms.inventoryservice.services.exceptions.InventoryServiceException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InventoryServiceException.class)
	protected ResponseEntity<ApiError> handleTodoServiceException(InventoryServiceException ise) {

		ApiError error = ApiError.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, ise.getMessage());

		return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
