package com.sandeeprm.oms.inventoryservice.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.LongStream;

import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.inventoryservice.repositories.entities.ProductInventory;

@Repository
public class InventoryRepository {

	private static final Map<Long, ProductInventory> productInventoryByProductIdMap = new ConcurrentHashMap<>();

	static {
		LongStream.range(0, 100).mapToObj(id -> {
			Long productId = id + 1;
			ProductInventory productInventory = new ProductInventory(productId, 10);
			return productInventory;
		}).forEach(pi -> productInventoryByProductIdMap.put(pi.getProductId(), pi));
	}

	public Optional<ProductInventory> getProductInventoryByProductId(Long productId) {
		Optional<ProductInventory> opi = Optional.ofNullable(productInventoryByProductIdMap.get(productId));
		return opi;
	}

	public List<ProductInventory> getProductsInventory() {
		List<ProductInventory> productsInventory = Arrays
				.asList(productInventoryByProductIdMap.values().toArray(new ProductInventory[1]));
		return productsInventory;
	}

	public void updateInventory(ProductInventory updatedProductInventory) {
		ProductInventory productInventory = productInventoryByProductIdMap.get(updatedProductInventory.getProductId());
		productInventory.setQuantity(updatedProductInventory.getQuantity());
	}

}
