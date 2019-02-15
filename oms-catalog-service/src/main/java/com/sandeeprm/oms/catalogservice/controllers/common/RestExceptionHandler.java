package com.sandeeprm.oms.catalogservice.controllers.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sandeeprm.oms.catalogservice.services.exceptions.ProductCatalogServiceException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductCatalogServiceException.class)
	protected ResponseEntity<ApiError> handleTodoServiceException(ProductCatalogServiceException pcse) {

		ApiError error = ApiError.newInstance(HttpStatus.INTERNAL_SERVER_ERROR, pcse.getMessage());

		return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
