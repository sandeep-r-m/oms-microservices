package com.sandeeprm.oms.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.orderservice.repositories.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
