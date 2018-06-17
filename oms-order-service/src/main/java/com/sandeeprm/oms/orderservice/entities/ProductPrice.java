package com.sandeeprm.oms.orderservice.entities;

import java.math.BigDecimal;

public class ProductPrice {

	private Long productId;

	private BigDecimal price;

	public ProductPrice() {
		super();
	}

	public ProductPrice(Long productId, BigDecimal price) {
		super();
		this.productId = productId;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
