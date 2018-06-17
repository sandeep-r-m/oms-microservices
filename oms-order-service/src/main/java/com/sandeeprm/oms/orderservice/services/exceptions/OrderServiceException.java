package com.sandeeprm.oms.orderservice.services.exceptions;

public class OrderServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderServiceException(String message) {
		super(message);
	}

	public OrderServiceException(Throwable cause) {
		super(cause);
	}

}
