package com.sandeeprm.oms.pricingservice.services.exceptions;

public class ProductPriceServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductPriceServiceException() {
		super();
	}

	public ProductPriceServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductPriceServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductPriceServiceException(String message) {
		super(message);
	}

	public ProductPriceServiceException(Throwable cause) {
		super(cause);
	}

	public static ProductPriceServiceException create(String message) {
		ProductPriceServiceException ppse = new ProductPriceServiceException(message);
		return ppse;
	}

}
