package com.sandeeprm.oms.pricingservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sandeeprm.oms.pricingservice.repositories.PricingRepository;
import com.sandeeprm.oms.pricingservice.repositories.domain.ProductPrice;
import com.sandeeprm.oms.pricingservice.services.PricingService;
import com.sandeeprm.oms.pricingservice.services.exceptions.ProductPriceServiceException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class PricingServiceImpl implements PricingService {

	@Autowired
	private PricingRepository pricingRepository;

	@Override
	public ProductPrice fetchProductPriceByProductId(Long productId) throws ProductPriceServiceException {

		Optional<ProductPrice> fetchedPrice = pricingRepository.findById(productId);

		ProductPrice price = fetchedPrice
				.orElseThrow(() -> ProductPriceServiceException.create("Product price not found"));

		return price;
	}

	@Override
	public List<ProductPrice> fetchAllProductsPrice() throws ProductPriceServiceException {

		List<ProductPrice> fetchedProductsPrice = pricingRepository.findAll();

		if (CollectionUtils.isEmpty(fetchedProductsPrice)) {
			throw ProductPriceServiceException.create("No products price found");
		}

		return fetchedProductsPrice;
	}

	@Override
	public ProductPrice saveProductPrice(ProductPrice price) throws ProductPriceServiceException {
		return pricingRepository.save(price);
	}

	@Override
	public void deleteProductPrice(long productId) throws ProductPriceServiceException {
		pricingRepository.deleteById(productId);
	}

}
