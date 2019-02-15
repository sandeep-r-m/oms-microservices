package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sandeeprm.oms.orderservice.controllers.resources.ProductPriceResource;

@FeignClient(value = "oms-pricing-service", fallback = PricingServiceFallback.class)
public interface PricingServiceClient {

	@GetMapping(value = "/prices/{productId}")
	public ProductPriceResource getPriceByProductId(@PathVariable("productId") Long productId);

}
