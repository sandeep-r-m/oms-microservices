package com.sandeeprm.oms.inventoryservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeeprm.oms.inventoryservice.repositories.InventoryRepository;
import com.sandeeprm.oms.inventoryservice.repositories.entities.ProductInventory;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public Optional<ProductInventory> getInventoryByProductId(Long productId) {
		if (null == productId)
			throw new IllegalArgumentException("product-id is not valid");
		Optional<ProductInventory> productInventory = inventoryRepository.getProductInventoryByProductId(productId);
		return productInventory;
	}

	public List<ProductInventory> getProductsInventory() {
		List<ProductInventory> productsInventory = inventoryRepository.getProductsInventory();
		return productsInventory;
	}

	public void decreaseInventory(Long productId, Integer decrease) {
		if (null == productId)
			throw new IllegalArgumentException("product-id is not valid");

		if (null == decrease || decrease <= 0)
			throw new IllegalArgumentException("decrease is not valid");

		Optional<ProductInventory> oppi = inventoryRepository.getProductInventoryByProductId(productId);
		if (!oppi.isPresent())
			return; // TODO: throw exception

		int oldQuantity = oppi.get().getQuantity();

		if (decrease > oldQuantity)
			return; // TODO: throw exception

		int newQuantity = oldQuantity - decrease;

		inventoryRepository.updateInventory(new ProductInventory(productId, newQuantity));
	}
}
