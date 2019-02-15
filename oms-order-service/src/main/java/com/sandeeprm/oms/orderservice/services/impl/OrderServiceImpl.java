package com.sandeeprm.oms.orderservice.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sandeeprm.oms.orderservice.controllers.resources.OrderRequestResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductPriceResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductResource;
import com.sandeeprm.oms.orderservice.repositories.OrderRepository;
import com.sandeeprm.oms.orderservice.repositories.domain.Order;
import com.sandeeprm.oms.orderservice.services.OrderService;
import com.sandeeprm.oms.orderservice.services.exceptions.OrderServiceException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> fetchAllOrders() throws OrderServiceException {

		List<Order> fetchedOrders = orderRepository.findAll();

		if (CollectionUtils.isEmpty(fetchedOrders))
			throw OrderServiceException.create("No orders found");

		return fetchedOrders;
	}

	@Override
	public Order fetchOrderById(long orderId) throws OrderServiceException {

		Optional<Order> fetchedOrder = orderRepository.findById(orderId);

		Order order = fetchedOrder.orElseThrow(() -> OrderServiceException.create("Order not found"));

		return order;
	}

	@Override
	public void deleteOrder(long orderId) throws OrderServiceException {
		orderRepository.deleteById(orderId);
	}

	@Override
	public Order processOrder(OrderRequestResource newOrderRequest, Optional<ProductResource> product,
			Optional<ProductInventoryResource> productInventory, Optional<ProductPriceResource> productPrice)
			throws OrderServiceException {

		ProductResource productResource = product
				.orElseThrow(() -> OrderServiceException.create("Error processing order - Product not found"));

		ProductInventoryResource productInventoryResource = productInventory
				.orElseThrow(() -> OrderServiceException.create("Error processing order - No product inventory"));

		ProductPriceResource productPriceResource = productPrice.orElseThrow(
				() -> OrderServiceException.create("Error processing order - Product price not available"));

		// Validate quantity
		if (newOrderRequest.getQuantity().intValue() > productInventoryResource.getQuantity().intValue())
			throw OrderServiceException.create("Error processing order - Requested product quantity not available");

		// Calculate total price
		BigDecimal unitProductPrice = productPriceResource.getPrice();
		BigDecimal totalPrice = unitProductPrice.multiply(new BigDecimal(newOrderRequest.getQuantity().intValue()));

		Order order = Order.newInstance(newOrderRequest.getUserId(), productResource.getProductId(),
				newOrderRequest.getQuantity(), totalPrice);

		// Save order
		order = orderRepository.save(order);

		return order;
	}

}
