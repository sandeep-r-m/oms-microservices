package com.sandeeprm.oms.catalogservice.controllers.common;

import com.sandeeprm.oms.catalogservice.controllers.resources.ProductResource;
import com.sandeeprm.oms.catalogservice.repositories.domain.Product;

public class ResourceToDomainMapper {

	public static Product map(ProductResource productResource) {
		Product product = Product.newInstance(productResource.getProductId(), productResource.getName(),
				productResource.getDesc());
		return product;
	}

}
