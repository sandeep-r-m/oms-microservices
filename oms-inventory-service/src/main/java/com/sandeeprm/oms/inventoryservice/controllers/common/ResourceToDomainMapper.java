package com.sandeeprm.oms.inventoryservice.controllers.common;

import com.sandeeprm.oms.inventoryservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.inventoryservice.repositories.domain.ProductInventory;

public class ResourceToDomainMapper {

	public static ProductInventory map(ProductInventoryResource productInventoryResource) {
		ProductInventory productInventory = ProductInventory.newInstance(productInventoryResource.getProductId(),
				productInventoryResource.getQuantity());
		return productInventory;
	}

}
