package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.stereotype.Component;

import com.sandeeprm.oms.orderservice.entities.ProductInventory;

@Component
public class InventoryServiceFallback implements InventoryServiceClient {

	@Override
	public ProductInventory getInventoryByProductId(Long productId) {
		ProductInventory productInventory = new ProductInventory(productId, 0);
		return productInventory;
	}

}
