package com.sandeeprm.oms.orderservice.controllers.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderResource {

	private Long userId;

	private Long orderId;

	private Long productId;

	private Integer quantity;

	private BigDecimal totalPrice;

	private LocalDateTime dateCreated;

	public static OrderResource newInstance(long userId, long orderId, long productId, int quantity,
			BigDecimal totalPrice, LocalDateTime dateCreated) {
		OrderResource orderResource = new OrderResource();
		orderResource.setUserId(userId);
		orderResource.setOrderId(orderId);
		orderResource.setProductId(productId);
		orderResource.setQuantity(quantity);
		orderResource.setTotalPrice(totalPrice);
		orderResource.setDateCreated(dateCreated);
		return orderResource;
	}

}
