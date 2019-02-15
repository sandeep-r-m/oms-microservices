package com.sandeeprm.oms.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.inventoryservice.repositories.domain.ProductInventory;

@Repository
public interface InventoryRepository extends JpaRepository<ProductInventory, Long> {

}
