package com.sandeeprm.oms.orderservice.controllers.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sandeeprm.oms.orderservice.controllers.feignclients.CatalogServiceClient;
import com.sandeeprm.oms.orderservice.controllers.feignclients.InventoryServiceClient;
import com.sandeeprm.oms.orderservice.controllers.feignclients.PricingServiceClient;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductPriceResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductResource;

@Component
public class ServiceCallerFeignClient {

	@Autowired
	private CatalogServiceClient productCatalogServiceClient;

	@Autowired
	private InventoryServiceClient inventoryServiceClient;

	@Autowired
	private PricingServiceClient pricingServiceClient;

	/**
	 * 
	 * @param productId
	 * @return
	 */
	public Optional<ProductPriceResource> getProductPriceByProductId(long productId) {
		ProductPriceResource productPrice = pricingServiceClient.getPriceByProductId(productId);
		return Optional.ofNullable(productPrice);
	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	public Optional<ProductResource> getProduct(long productId) {
		ProductResource productResource = productCatalogServiceClient.getProductById(productId);
		return Optional.ofNullable(productResource);
	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	public Optional<ProductInventoryResource> getProductInventoryByProductId(long productId) {
		ProductInventoryResource productInventoryResource = inventoryServiceClient.getInventoryByProductId(productId);
		return Optional.ofNullable(productInventoryResource);
	}

}
