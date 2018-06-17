package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.stereotype.Component;

import com.sandeeprm.oms.orderservice.entities.Product;

@Component
public class CatalogServiceFallback implements CatalogServiceClient {

	@Override
	public Product getProductById(Long id) {
		return null;
	}

}
