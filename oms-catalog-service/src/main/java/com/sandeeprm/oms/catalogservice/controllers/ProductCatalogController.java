package com.sandeeprm.oms.catalogservice.controllers;

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

import com.sandeeprm.oms.catalogservice.controllers.common.DomainToResourceMapper;
import com.sandeeprm.oms.catalogservice.controllers.common.ResourceToDomainMapper;
import com.sandeeprm.oms.catalogservice.controllers.resources.ProductResource;
import com.sandeeprm.oms.catalogservice.repositories.domain.Product;
import com.sandeeprm.oms.catalogservice.services.ProductCatalogService;

@RestController
@RequestMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductCatalogController {

	@Autowired
	private ProductCatalogService productCatalogService;

	@GetMapping
	public ResponseEntity<List<ProductResource>> fetchAllProducts() {

		List<Product> fetchedProducts = productCatalogService.fetchAllProducts();

		List<ProductResource> productResources = DomainToResourceMapper.map(fetchedProducts);

		return new ResponseEntity<>(productResources, HttpStatus.OK);
	}

	@GetMapping(path = "/{productId}")
	public ResponseEntity<ProductResource> fetchProductById(@PathVariable("productId") long productId) {

		Product fetchedProduct = productCatalogService.fetchProductById(productId);

		ProductResource productResource = DomainToResourceMapper.map(fetchedProduct);

		return new ResponseEntity<>(productResource, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProductResource> saveProduct(@RequestBody ProductResource productResource) {

		Product product = ResourceToDomainMapper.map(productResource);

		product = productCatalogService.saveProduct(product);

		ProductResource savedProductResource = DomainToResourceMapper.map(product);

		return new ResponseEntity<>(savedProductResource, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{productId}")
	public ResponseEntity<ProductResource> deleteProduct(@PathVariable("productId") long productId) {

		productCatalogService.deleteProductById(productId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
