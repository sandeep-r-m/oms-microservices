package com.sandeeprm.oms.catalogservice.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.LongStream;

import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.catalogservice.entities.Product;

@Repository
public class ProductCatalogRepository {

	private static final Map<Long, Product> productsMap = new ConcurrentHashMap<>();

	static {
		LongStream.range(0, 100).mapToObj(i -> {
			Long id = i + 1L;
			return new Product(id, "PRODUCTNAME" + id, "PRODUCTDESC" + id);
		}).forEach(p -> productsMap.put(p.getId(), p));
	}

	public Optional<Product> getProductById(Long id) {
		Optional<Product> op = Optional.ofNullable(productsMap.get(id));
		return op;
	}

	public List<Product> getProducts() {
		List<Product> products = Arrays.asList(productsMap.values().toArray(new Product[1]));
		return products;
	}

}
