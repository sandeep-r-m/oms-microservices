package com.sandeeprm.oms.pricingservice.repositories;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.LongStream;

import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.pricingservice.domain.ProductPrice;

@Repository
public class PricingRepository {

	private static final Map<Long, ProductPrice> productsPriceMap = new ConcurrentHashMap<>();

	static {
		LongStream.range(0, 100).mapToObj(idx -> {
			Long productId = idx + 1;
			ProductPrice price = new ProductPrice(productId, new BigDecimal(100.00F));
			return price;
		}).forEach(pp -> productsPriceMap.put(pp.getProductId(), pp));
	}

	public Optional<ProductPrice> getPriceByProductId(Long id) {
		Optional<ProductPrice> opp = Optional.ofNullable(productsPriceMap.get(id));
		return opp;
	}

	public List<ProductPrice> getProductsPrice() {
		List<ProductPrice> productsPrice = Arrays.asList(productsPriceMap.values().toArray(new ProductPrice[1]));
		return productsPrice;
	}

}
