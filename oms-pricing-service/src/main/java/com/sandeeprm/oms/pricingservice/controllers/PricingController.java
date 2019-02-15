package com.sandeeprm.oms.pricingservice.controllers;

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

import com.sandeeprm.oms.pricingservice.controllers.common.DomainToResourceMapper;
import com.sandeeprm.oms.pricingservice.controllers.common.ResourceToDomainMapper;
import com.sandeeprm.oms.pricingservice.controllers.resources.ProductPriceResource;
import com.sandeeprm.oms.pricingservice.repositories.domain.ProductPrice;
import com.sandeeprm.oms.pricingservice.services.PricingService;

@RestController
@RequestMapping(value = "/prices", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PricingController {

	@Autowired
	private PricingService pricingService;

	@GetMapping
	public ResponseEntity<List<ProductPriceResource>> getProductsPrice() {

		List<ProductPrice> fetchedProductsPrice = pricingService.fetchAllProductsPrice();

		List<ProductPriceResource> productsPriceResource = DomainToResourceMapper.map(fetchedProductsPrice);

		return new ResponseEntity<>(productsPriceResource, HttpStatus.OK);
	}

	@GetMapping(value = "/{productId}")
	public ResponseEntity<ProductPriceResource> getProductPriceByProductId(@PathVariable("productId") Long productId) {

		ProductPrice fetchedProductPrice = pricingService.fetchProductPriceByProductId(productId);

		ProductPriceResource productPriceResource = DomainToResourceMapper.map(fetchedProductPrice);

		return new ResponseEntity<>(productPriceResource, HttpStatus.OK);
	}

	@PostMapping(path = "/prices/{productId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProductPriceResource> saveProductPrice(@PathVariable("productId") Long productId,
			@RequestBody ProductPriceResource productPriceResource) {

		// TODO : Validate product id by calling product catalog api

		ProductPrice productPrice = ResourceToDomainMapper.map(productPriceResource);
		productPrice.setProductId(productId);

		productPrice = pricingService.saveProductPrice(productPrice);

		ProductPriceResource savedProductPriceResource = DomainToResourceMapper.map(productPrice);

		return new ResponseEntity<ProductPriceResource>(savedProductPriceResource, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{productId}")
	public ResponseEntity<ProductPriceResource> deleteProductPrice(@PathVariable("productId") long productId) {

		pricingService.deleteProductPrice(productId);

		return new ResponseEntity<ProductPriceResource>(HttpStatus.OK);
	}

}
