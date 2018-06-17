package com.sandeeprm.oms.pricingservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeeprm.oms.pricingservice.domain.ProductPrice;
import com.sandeeprm.oms.pricingservice.services.PricingService;

@RestController
@RequestMapping(value = "/prices")
public class PricingController {

	@Autowired
	private PricingService pricingService;

	@GetMapping
	public ResponseEntity<List<ProductPrice>> getProductsPrice() {
		List<ProductPrice> productsPrice = pricingService.getProductsPrice();
		if (productsPrice.size() <= 0)
			return new ResponseEntity<List<ProductPrice>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<ProductPrice>>(productsPrice, HttpStatus.OK);
	}

	@GetMapping(value = "/{product-id}")
	public ResponseEntity<ProductPrice> getPriceByProductId(@PathVariable("product-id") Long id) {
		Optional<ProductPrice> opp = pricingService.getPriceByProductId(id);
		if (!opp.isPresent())
			return new ResponseEntity<ProductPrice>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ProductPrice>(opp.get(), HttpStatus.OK);
	}

}
