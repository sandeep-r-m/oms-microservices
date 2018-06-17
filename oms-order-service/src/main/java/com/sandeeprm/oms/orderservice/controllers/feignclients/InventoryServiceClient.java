package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sandeeprm.oms.orderservice.entities.ProductInventory;

@FeignClient(value = "oms-inventory-service", fallback = InventoryServiceFallback.class)
public interface InventoryServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/inventories/{product-id}")
	public ProductInventory getInventoryByProductId(@PathVariable("product-id") Long productId);

}
