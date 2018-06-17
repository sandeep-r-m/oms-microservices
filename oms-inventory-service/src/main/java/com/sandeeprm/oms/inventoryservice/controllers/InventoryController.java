package com.sandeeprm.oms.inventoryservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeeprm.oms.inventoryservice.repositories.entities.ProductInventory;
import com.sandeeprm.oms.inventoryservice.services.InventoryService;

@RestController
@RequestMapping(value = "/inventories")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@GetMapping
	public ResponseEntity<List<ProductInventory>> getProductsInventory() {

		List<ProductInventory> productsInventory = inventoryService.getProductsInventory();

		if (productsInventory.size() <= 0) {
			return new ResponseEntity<List<ProductInventory>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<ProductInventory>>(productsInventory, HttpStatus.OK);
	}

	@GetMapping(value = "/{product-id}")
	public ResponseEntity<ProductInventory> getInventoryByProductId(@PathVariable(name = "product-id") Long productId) {

		Optional<ProductInventory> opi = inventoryService.getInventoryByProductId(productId);

		if (!opi.isPresent()) {
			return new ResponseEntity<ProductInventory>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProductInventory>(opi.get(), HttpStatus.OK);
	}

}
