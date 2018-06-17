package com.sandeeprm.oms.orderservice.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.sandeeprm.oms.orderservice.entities.Order;

@Repository
public class OrderRepository {

	private static AtomicLong orderIdSequence = new AtomicLong();

	private static Map<Long, Order> ordersByIdMap = new ConcurrentHashMap<>();

	public List<Order> getOrders() {
		List<Order> orderList = Arrays.asList(ordersByIdMap.values().toArray(new Order[1]));
		return orderList;
	}

	public Optional<Order> getOrderById(Long id) {
		Optional<Order> op = Optional.ofNullable(ordersByIdMap.get(id));
		return op;
	}

	public Long save(Order newOrder) {
		newOrder.setId(orderIdSequence.incrementAndGet());
		ordersByIdMap.put(newOrder.getId(), newOrder);
		return newOrder.getId();
	}

}
