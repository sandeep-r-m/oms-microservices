package com.sandeeprm.oms.inventoryservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeeprm.oms.inventoryservice.controllers.common.DomainToResourceMapper;
import com.sandeeprm.oms.inventoryservice.controllers.common.ResourceToDomainMapper;
import com.sandeeprm.oms.inventoryservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.inventoryservice.repositories.domain.ProductInventory;
import com.sandeeprm.oms.inventoryservice.services.InventoryService;

@RestController
@RequestMapping(value = "/inventories", produces = { MediaType.APPLICATION_JSON_VALUE })
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping
	public ResponseEntity<List<ProductInventoryResource>> fetchAllProductsInventory() {

		List<ProductInventory> fetchedProductsInventory = inventoryService.fetchAllProductsInventory();

		List<ProductInventoryResource> productInventoryResources = DomainToResourceMapper.map(fetchedProductsInventory);

		return new ResponseEntity<>(productInventoryResources, HttpStatus.OK);
	}

	@GetMapping(path = "/{productId}")
	public ResponseEntity<ProductInventoryResource> fetchProductInventoryByProductId(
			@PathVariable("productId") long productId) {

		ProductInventory fetchedProductInventory = inventoryService.fetchProductInventoryByProductId(productId);

		ProductInventoryResource productInventoryResource = DomainToResourceMapper.map(fetchedProductInventory);

		return new ResponseEntity<>(productInventoryResource, HttpStatus.OK);
	}

	@PostMapping(path = "/{productId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProductInventoryResource> saveProductInventory(@PathVariable("productId") long productId,
			@RequestBody ProductInventoryResource productInventoryResource) {

		// TODO : Validate product id by calling product catalog api

		ProductInventory productInventory = ResourceToDomainMapper.map(productInventoryResource);
		productInventory.setProductId(productId);

		productInventory = inventoryService.saveProductInventory(productInventory);

		ProductInventoryResource savedProductInventoryResource = DomainToResourceMapper.map(productInventory);

		return new ResponseEntity<>(savedProductInventoryResource, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{productId}")
	public ResponseEntity<ProductInventoryResource> deleteProductInventory(@PathVariable("productId") long productId) {

		inventoryService.deleteProductInventory(productId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
