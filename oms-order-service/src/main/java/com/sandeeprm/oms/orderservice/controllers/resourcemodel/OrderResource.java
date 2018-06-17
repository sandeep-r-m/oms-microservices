package com.sandeeprm.oms.orderservice.controllers.resourcemodel;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.sandeeprm.oms.orderservice.controllers.OrderController;

public class OrderResource extends ResourceSupport {

	private Long orderId;

	private String status;

	public OrderResource(Long orderId, String status) {
		super();
		this.orderId = orderId;
		this.status = status;
		add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(OrderController.class).getOrderById(orderId))
				.withSelfRel());
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
