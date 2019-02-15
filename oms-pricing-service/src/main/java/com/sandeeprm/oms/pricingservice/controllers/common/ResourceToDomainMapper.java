package com.sandeeprm.oms.pricingservice.controllers.common;

import com.sandeeprm.oms.pricingservice.controllers.resources.ProductPriceResource;
import com.sandeeprm.oms.pricingservice.repositories.domain.ProductPrice;

public class ResourceToDomainMapper {

	public static ProductPrice map(ProductPriceResource productPriceResource) {
		ProductPrice productPrice = ProductPrice.newInstance(productPriceResource.getProductId(),
				productPriceResource.getPrice());
		return productPrice;
	}

}
