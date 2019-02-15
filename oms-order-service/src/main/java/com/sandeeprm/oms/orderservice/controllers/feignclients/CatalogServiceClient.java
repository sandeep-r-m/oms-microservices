package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sandeeprm.oms.orderservice.controllers.resources.ProductResource;

@FeignClient(value = "oms-catalog-service", fallback = CatalogServiceFallback.class)
public interface CatalogServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
	public ProductResource getProductById(@PathVariable("productId") Long id);

}
