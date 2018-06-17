package com.sandeeprm.oms.pricingservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeeprm.oms.pricingservice.domain.ProductPrice;
import com.sandeeprm.oms.pricingservice.repositories.PricingRepository;

@Service
public class PricingService {

	@Autowired
	private PricingRepository pricingRepository;

	public Optional<ProductPrice> getPriceByProductId(Long id) {
		Optional<ProductPrice> opp = pricingRepository.getPriceByProductId(id);
		return opp;
	}

	public List<ProductPrice> getProductsPrice() {
		List<ProductPrice> productsPrice = pricingRepository.getProductsPrice();
		return productsPrice;
	}

}
