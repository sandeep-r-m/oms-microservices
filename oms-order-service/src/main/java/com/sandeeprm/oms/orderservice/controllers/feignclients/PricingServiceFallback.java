package com.sandeeprm.oms.orderservice.controllers.feignclients;

import org.springframework.stereotype.Component;

import com.sandeeprm.oms.orderservice.controllers.resources.ProductPriceResource;

@Component
public class PricingServiceFallback implements PricingServiceClient {

	@Override
	public ProductPriceResource getPriceByProductId(Long productId) {
		return null;
	}

}
