package com.sandeeprm.oms.catalogservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeeprm.oms.catalogservice.entities.Product;
import com.sandeeprm.oms.catalogservice.repositories.ProductCatalogRepository;

@Service
public class ProductCatalogService {

	@Autowired
	private ProductCatalogRepository productCatalogRepository;

	public Optional<Product> getProductById(Long id) {
		if (null == id) {
			throw new IllegalArgumentException("product-id is invalid");
		}
		Optional<Product> product = productCatalogRepository.getProductById(id);
		return product;
	}

	public List<Product> getProducts() {
		List<Product> productList = productCatalogRepository.getProducts();
		return productList;
	}

}
