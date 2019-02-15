package com.sandeeprm.oms.pricingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.pricingservice.repositories.domain.ProductPrice;

@Repository
public interface PricingRepository extends JpaRepository<ProductPrice, Long> {

}
