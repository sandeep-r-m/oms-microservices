package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sandeeprm.oms.orderservice.controllers.resources.ProductInventoryResource;

@FeignClient(value = "oms-inventory-service", fallback = InventoryServiceFallback.class)
public interface InventoryServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/inventories/{productId}")
	public ProductInventoryResource getInventoryByProductId(@PathVariable("productId") Long productId);

}
