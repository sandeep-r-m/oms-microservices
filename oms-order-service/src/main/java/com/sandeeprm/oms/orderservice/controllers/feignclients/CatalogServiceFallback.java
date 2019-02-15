package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.stereotype.Component;

import com.sandeeprm.oms.orderservice.controllers.resources.ProductResource;

@Component
public class CatalogServiceFallback implements CatalogServiceClient {

	@Override
	public ProductResource getProductById(Long id) {
		return null;
	}

}
