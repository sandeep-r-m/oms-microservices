package com.sandeeprm.oms.catalogservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.catalogservice.repositories.domain.Product;

@Repository
public interface ProductCatalogRepository extends JpaRepository<Product, Long> {

}
