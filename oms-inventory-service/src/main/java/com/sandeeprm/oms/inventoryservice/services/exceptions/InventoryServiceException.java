package com.sandeeprm.oms.inventoryservice.services.exceptions;

public class InventoryServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryServiceException() {
		super();
	}

	public InventoryServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InventoryServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryServiceException(String message) {
		super(message);
	}

	public InventoryServiceException(Throwable cause) {
		super(cause);
	}

	public static InventoryServiceException create(String message) {
		return new InventoryServiceException(message);
	}
}
