package com.sandeeprm.oms.orderservice.repositories.domain;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "OMS_ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private Long orderId;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_QUANTITY")
	private Integer quantity;

	@Column(name = "TOTAL_PRICE")
	private BigDecimal totalPrice;

	@Column(name = "DT_CREATED")
	private LocalDateTime dateCreated;

	@Column(name = "USER_ID")
	private Long userId;

	public static Order newInstance(long userId, long productId, int quantity, BigDecimal totalPrice) {
		Order order = new Order();
		order.setUserId(userId);
		order.setProductId(productId);
		order.setQuantity(quantity);
		order.setTotalPrice(totalPrice);
		return order;
	}

}
