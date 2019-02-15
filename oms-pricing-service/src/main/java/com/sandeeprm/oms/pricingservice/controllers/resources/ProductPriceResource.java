package com.sandeeprm.oms.pricingservice.controllers.resources;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPriceResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long productId;

	private BigDecimal price;

	public static ProductPriceResource newInstance(Long productId, BigDecimal price) {
		ProductPriceResource pp = new ProductPriceResource();
		pp.setProductId(productId);
		pp.setPrice(price);
		return pp;
	}

}
