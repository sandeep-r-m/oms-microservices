package com.sandeeprm.oms.orderservice.controllers.common;

import java.util.List;
import java.util.stream.Collectors;

import com.sandeeprm.oms.orderservice.controllers.resources.OrderResource;
import com.sandeeprm.oms.orderservice.repositories.domain.Order;

public class DomainToResourceMapper {

	public static OrderResource map(Order order) {
		OrderResource orderResource = OrderResource.newInstance(order.getUserId(), order.getOrderId(),
				order.getProductId(), order.getQuantity(), order.getTotalPrice(), order.getDateCreated());
		return orderResource;
	}

	public static List<OrderResource> map(List<Order> orders) {
		List<OrderResource> orderResources = orders.stream()
				.map(order -> OrderResource.newInstance(order.getUserId(), order.getOrderId(), order.getProductId(),
						order.getQuantity(), order.getTotalPrice(), order.getDateCreated()))
				.collect(Collectors.toList());
		return orderResources;
	}

}
