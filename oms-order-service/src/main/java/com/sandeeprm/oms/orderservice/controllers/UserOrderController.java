package com.sandeeprm.oms.orderservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeeprm.oms.orderservice.controllers.common.DomainToResourceMapper;
import com.sandeeprm.oms.orderservice.controllers.common.ServiceCallerFeignClient;
import com.sandeeprm.oms.orderservice.controllers.resources.OrderRequestResource;
import com.sandeeprm.oms.orderservice.controllers.resources.OrderResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductPriceResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductResource;
import com.sandeeprm.oms.orderservice.repositories.domain.Order;
import com.sandeeprm.oms.orderservice.services.OrderService;

@RestController
@RequestMapping(value = "/u-orders", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ServiceCallerFeignClient serviceClient;

	@GetMapping(path = "/{userId}")
	public ResponseEntity<List<OrderResource>> getAllOrdersByUserId(@PathVariable("userId") long userId) {

		List<Order> orders = orderService.fetchAllOrders();

		List<OrderResource> orderResources = DomainToResourceMapper.map(orders);

		return new ResponseEntity<>(orderResources, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/{orderId}")
	public ResponseEntity<OrderResource> getOrderByUserIdAndOrderId(@PathVariable("userId") long userId,
			@PathVariable("orderId") long orderId) {

		Order fetchedOrder = orderService.fetchOrderById(orderId);

		OrderResource orderResource = DomainToResourceMapper.map(fetchedOrder);

		return new ResponseEntity<>(orderResource, HttpStatus.OK);
	}

	@PostMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<OrderResource> submitUserOrder(@PathVariable("userId") long userId,
			@RequestBody OrderRequestResource newOrderRequest) {

		// Get Product details
		Optional<ProductResource> product = serviceClient.getProduct(newOrderRequest.getProductId());

		// Get Inventory details
		Optional<ProductInventoryResource> productInventory = serviceClient
				.getProductInventoryByProductId(newOrderRequest.getProductId());

		// Get Price details
		Optional<ProductPriceResource> productPrice = serviceClient
				.getProductPriceByProductId(newOrderRequest.getProductId());

		// Try to process order
		Order savedOrder = orderService.processOrder(newOrderRequest, product, productInventory, productPrice);

		OrderResource orderResource = DomainToResourceMapper.map(savedOrder);

		return new ResponseEntity<>(orderResource, HttpStatus.OK);

	}

	@DeleteMapping(path = "/{userId}/{orderId}")
	public ResponseEntity<OrderResource> cancelUserOrder(@PathVariable("userId") long userId,
			@PathVariable("orderId") long orderId) {

		orderService.deleteOrder(orderId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
