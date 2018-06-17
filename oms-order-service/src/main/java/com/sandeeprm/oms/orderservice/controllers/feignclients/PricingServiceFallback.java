package com.sandeeprm.oms.orderservice.controllers.feignclients;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.sandeeprm.oms.orderservice.entities.ProductPrice;

@Component
public class PricingServiceFallback implements PricingServiceClient {

	@Override
	public ProductPrice getPriceByProductId(Long productId) {
		return new ProductPrice(productId, new BigDecimal(99999.99F));
	}

}
