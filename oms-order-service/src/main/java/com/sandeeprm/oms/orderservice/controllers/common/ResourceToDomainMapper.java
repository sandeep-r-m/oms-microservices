package com.sandeeprm.oms.orderservice.controllers.common;

import com.sandeeprm.oms.orderservice.controllers.resources.OrderResource;
import com.sandeeprm.oms.orderservice.repositories.domain.Order;

public class ResourceToDomainMapper {

	public static Order map(OrderResource orderResource) {
		Order order = Order.newInstance(orderResource.getUserId(), orderResource.getProductId(),
				orderResource.getQuantity(), orderResource.getTotalPrice());
		return order;
	}

}
