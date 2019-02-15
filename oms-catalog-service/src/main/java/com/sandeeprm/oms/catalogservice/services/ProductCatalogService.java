package com.sandeeprm.oms.catalogservice.services;

import java.util.List;

import com.sandeeprm.oms.catalogservice.repositories.domain.Product;
import com.sandeeprm.oms.catalogservice.services.exceptions.ProductCatalogServiceException;

public interface ProductCatalogService {

	public List<Product> fetchAllProducts() throws ProductCatalogServiceException;

	public Product fetchProductById(long productId) throws ProductCatalogServiceException;

	public Product saveProduct(Product product) throws ProductCatalogServiceException;

	public void deleteProductById(long productId) throws ProductCatalogServiceException;
}
