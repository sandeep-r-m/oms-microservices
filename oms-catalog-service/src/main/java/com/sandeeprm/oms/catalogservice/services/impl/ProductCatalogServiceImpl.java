package com.sandeeprm.oms.catalogservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sandeeprm.oms.catalogservice.repositories.ProductCatalogRepository;
import com.sandeeprm.oms.catalogservice.repositories.domain.Product;
import com.sandeeprm.oms.catalogservice.services.ProductCatalogService;
import com.sandeeprm.oms.catalogservice.services.exceptions.ProductCatalogServiceException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class ProductCatalogServiceImpl implements ProductCatalogService {

	@Autowired
	private ProductCatalogRepository productCatalogRepository;

	@Override
	public List<Product> fetchAllProducts() throws ProductCatalogServiceException {

		List<Product> fetchedProducts = productCatalogRepository.findAll();

		if (CollectionUtils.isEmpty(fetchedProducts))
			throw ProductCatalogServiceException.create("No products found");

		return fetchedProducts;
	}

	@Override
	public Product fetchProductById(long productId) throws ProductCatalogServiceException {

		Optional<Product> fetchedProduct = productCatalogRepository.findById(productId);

		Product product = fetchedProduct.orElseThrow(() -> ProductCatalogServiceException.create("Product not found"));

		return product;
	}

	@Override
	public Product saveProduct(Product product) throws ProductCatalogServiceException {

		Product savedProduct = productCatalogRepository.save(product);

		return savedProduct;
	}

	@Override
	public void deleteProductById(long productId) throws ProductCatalogServiceException {

		productCatalogRepository.deleteById(productId);
	}

}
