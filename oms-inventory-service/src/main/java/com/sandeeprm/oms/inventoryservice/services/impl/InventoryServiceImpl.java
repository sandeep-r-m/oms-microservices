package com.sandeeprm.oms.inventoryservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sandeeprm.oms.inventoryservice.repositories.InventoryRepository;
import com.sandeeprm.oms.inventoryservice.repositories.domain.ProductInventory;
import com.sandeeprm.oms.inventoryservice.services.InventoryService;
import com.sandeeprm.oms.inventoryservice.services.exceptions.InventoryServiceException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public List<ProductInventory> fetchAllProductsInventory() throws InventoryServiceException {

		List<ProductInventory> fetchedProductsInventory = inventoryRepository.findAll();

		if (CollectionUtils.isEmpty(fetchedProductsInventory))
			throw InventoryServiceException.create("No products inventory found");

		return fetchedProductsInventory;
	}

	@Override
	public ProductInventory fetchProductInventoryByProductId(long productId) throws InventoryServiceException {

		Optional<ProductInventory> fetchProductInventory = inventoryRepository.findById(productId);

		ProductInventory productInventory = fetchProductInventory
				.orElseThrow(() -> InventoryServiceException.create("Product inventory not found"));

		return productInventory;
	}

	@Override
	public ProductInventory saveProductInventory(ProductInventory productInventory) throws InventoryServiceException {
		return inventoryRepository.save(productInventory);
	}

	@Override
	public void deleteProductInventory(long productId) throws InventoryServiceException {
		inventoryRepository.deleteById(productId);
	}

}
