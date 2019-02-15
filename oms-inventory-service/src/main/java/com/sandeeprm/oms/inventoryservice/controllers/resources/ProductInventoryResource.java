package com.sandeeprm.oms.inventoryservice.controllers.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductInventoryResource {

	private Long productId;

	private Integer quantity;

	public static ProductInventoryResource newInstance(Long productId, Integer quantity) {
		ProductInventoryResource pi = new ProductInventoryResource();
		pi.setProductId(productId);
		pi.setQuantity(quantity);
		return pi;
	}

}
