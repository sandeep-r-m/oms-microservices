package com.sandeeprm.oms.orderservice.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

	private Long id = new Long(0);

	private Long productId = null;

	private Integer quantity = new Integer(0);

	private BigDecimal totalPrice = new BigDecimal(0.0F);

	private LocalDateTime orderDateTime = LocalDateTime.now();

	public Order() {
		super();
	}

	public Order(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.orderDateTime = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

}
