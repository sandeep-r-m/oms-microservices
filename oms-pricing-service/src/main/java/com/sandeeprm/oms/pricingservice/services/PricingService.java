package com.sandeeprm.oms.pricingservice.services;

import java.util.List;

import com.sandeeprm.oms.pricingservice.repositories.domain.ProductPrice;
import com.sandeeprm.oms.pricingservice.services.exceptions.ProductPriceServiceException;

public interface PricingService {

	public ProductPrice fetchProductPriceByProductId(Long productId) throws ProductPriceServiceException;

	public List<ProductPrice> fetchAllProductsPrice() throws ProductPriceServiceException;

	public ProductPrice saveProductPrice(ProductPrice price) throws ProductPriceServiceException;

	public void deleteProductPrice(long productId) throws ProductPriceServiceException;

}
