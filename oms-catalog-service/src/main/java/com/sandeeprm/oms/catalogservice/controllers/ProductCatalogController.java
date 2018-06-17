package com.sandeeprm.oms.catalogservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeeprm.oms.catalogservice.entities.Product;
import com.sandeeprm.oms.catalogservice.services.ProductCatalogService;

@RestController
@RequestMapping(value = "/products")
public class ProductCatalogController {

	@Autowired
	private ProductCatalogService productCatalogService;

	@GetMapping
	public ResponseEntity<List<Product>> all() {

		List<Product> products = productCatalogService.getProducts();

		if (products.size() <= 0)
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {

		Optional<Product> product = productCatalogService.getProductById(id);

		if (!product.isPresent())
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}

}
