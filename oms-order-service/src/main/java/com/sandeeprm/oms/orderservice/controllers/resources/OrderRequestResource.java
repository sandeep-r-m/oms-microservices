package com.sandeeprm.oms.orderservice.controllers.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRequestResource {

	private Long userId;

	private Long productId;

	private Integer quantity;

}
