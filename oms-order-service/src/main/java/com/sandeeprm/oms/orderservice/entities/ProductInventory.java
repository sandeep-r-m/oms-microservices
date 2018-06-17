package com.sandeeprm.oms.orderservice.entities;

public class ProductInventory {

	private Long productId;

	private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductInventory() {
		super();
	}

	public ProductInventory(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

}
