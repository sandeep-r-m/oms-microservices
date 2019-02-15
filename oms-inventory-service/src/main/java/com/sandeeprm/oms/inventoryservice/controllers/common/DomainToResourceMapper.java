package com.sandeeprm.oms.inventoryservice.controllers.common;

import java.util.List;
import java.util.stream.Collectors;

import com.sandeeprm.oms.inventoryservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.inventoryservice.repositories.domain.ProductInventory;

public class DomainToResourceMapper {

	public static ProductInventoryResource map(ProductInventory productInventory) {
		ProductInventoryResource productInventoryResource = ProductInventoryResource
				.newInstance(productInventory.getProductId(), productInventory.getQuantity());
		return productInventoryResource;
	}

	public static List<ProductInventoryResource> map(List<ProductInventory> productInventories) {
		List<ProductInventoryResource> productInventoryResources = productInventories.stream()
				.map(productInventory -> ProductInventoryResource.newInstance(productInventory.getProductId(),
						productInventory.getQuantity()))
				.collect(Collectors.toList());
		return productInventoryResources;
	}

}
