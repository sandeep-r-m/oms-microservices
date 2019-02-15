package com.sandeeprm.oms.catalogservice.services.exceptions;

public class ProductCatalogServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductCatalogServiceException() {
	}

	public ProductCatalogServiceException(String message) {
		super(message);
	}

	public ProductCatalogServiceException(Throwable cause) {
		super(cause);
	}

	public ProductCatalogServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductCatalogServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public static ProductCatalogServiceException create(String message) {
		ProductCatalogServiceException pcse = new ProductCatalogServiceException(message);
		return pcse;
	}

}
