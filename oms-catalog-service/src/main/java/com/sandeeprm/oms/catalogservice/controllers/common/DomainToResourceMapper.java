package com.sandeeprm.oms.catalogservice.controllers.common;

import java.util.List;
import java.util.stream.Collectors;

import com.sandeeprm.oms.catalogservice.controllers.resources.ProductResource;
import com.sandeeprm.oms.catalogservice.repositories.domain.Product;

public class DomainToResourceMapper {

	public static ProductResource map(Product product) {
		ProductResource productResource = ProductResource.newInstance(product.getProductId(), product.getName(),
				product.getDesc(), product.getDateCreated(), product.getDateUpdated());
		return productResource;
	}

	public static List<ProductResource> map(List<Product> products) {
		List<ProductResource> productResources = products.stream()
				.map(product -> ProductResource.newInstance(product.getProductId(), product.getName(),
						product.getDesc(), product.getDateCreated(), product.getDateUpdated()))
				.collect(Collectors.toList());
		return productResources;
	}

}
