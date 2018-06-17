package com.sandeeprm.oms.orderservice.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeeprm.oms.orderservice.controllers.resourcemodel.OrderRequestResource;
import com.sandeeprm.oms.orderservice.entities.Order;
import com.sandeeprm.oms.orderservice.entities.Product;
import com.sandeeprm.oms.orderservice.entities.ProductInventory;
import com.sandeeprm.oms.orderservice.entities.ProductPrice;
import com.sandeeprm.oms.orderservice.repositories.OrderRepository;
import com.sandeeprm.oms.orderservice.services.exceptions.OrderServiceException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getOrders() {
		List<Order> orderList = orderRepository.getOrders();
		return orderList;
	}

	public Optional<Order> getOrderById(Long id) {
		if (null == id)
			throw new IllegalArgumentException("order id is not valid");
		Optional<Order> order = orderRepository.getOrderById(id);
		return order;
	}

	public Long save(OrderRequestResource newOrderRequest, Product product, ProductInventory productInventory,
			ProductPrice productPrice) throws OrderServiceException {

		if (newOrderRequest.getQuantity() <= 0)
			throw new OrderServiceException("Please provide valid order quantity");

		if (newOrderRequest.getQuantity() > productInventory.getQuantity())
			throw new OrderServiceException(
					"We can not accept an order for requested quantities. Please try to submit an order with reduced quantities");

		// Create a new order
		Order newOrder = new Order(newOrderRequest.getProductId(), newOrderRequest.getQuantity());

		// Calculate order total price
		BigDecimal totalPrice = productPrice.getPrice().multiply(new BigDecimal(newOrder.getQuantity()));
		newOrder.setTotalPrice(totalPrice);

		Long newOrderId = orderRepository.save(newOrder);

		return newOrderId;
	}
}
