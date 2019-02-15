package com.sandeeprm.oms.pricingservice.controllers.common;

import java.util.List;
import java.util.stream.Collectors;

import com.sandeeprm.oms.pricingservice.controllers.resources.ProductPriceResource;
import com.sandeeprm.oms.pricingservice.repositories.domain.ProductPrice;

public class DomainToResourceMapper {

	public static ProductPriceResource map(ProductPrice price) {
		ProductPriceResource priceResource = ProductPriceResource.newInstance(price.getProductId(), price.getPrice());
		return priceResource;
	}

	public static List<ProductPriceResource> map(List<ProductPrice> prices) {
		List<ProductPriceResource> pricesResource = prices.stream()
				.map(price -> ProductPriceResource.newInstance(price.getProductId(), price.getPrice()))
				.collect(Collectors.toList());
		return pricesResource;
	}

}
