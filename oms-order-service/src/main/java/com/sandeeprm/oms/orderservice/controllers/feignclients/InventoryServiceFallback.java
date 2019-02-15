package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.stereotype.Component;

import com.sandeeprm.oms.orderservice.controllers.resources.ProductInventoryResource;

@Component
public class InventoryServiceFallback implements InventoryServiceClient {

	@Override
	public ProductInventoryResource getInventoryByProductId(Long productId) {
		return null;
	}

}
