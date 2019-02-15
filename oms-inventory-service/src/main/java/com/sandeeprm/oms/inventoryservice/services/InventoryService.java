package com.sandeeprm.oms.inventoryservice.services;

import java.util.List;

import com.sandeeprm.oms.inventoryservice.repositories.domain.ProductInventory;
import com.sandeeprm.oms.inventoryservice.services.exceptions.InventoryServiceException;

public interface InventoryService {

	public List<ProductInventory> fetchAllProductsInventory() throws InventoryServiceException;

	public ProductInventory fetchProductInventoryByProductId(long productId) throws InventoryServiceException;

	public ProductInventory saveProductInventory(ProductInventory productInventory) throws InventoryServiceException;

	public void deleteProductInventory(long productId) throws InventoryServiceException;

}
