package com.sandeeprm.oms.orderservice.services;

import java.util.List;
import java.util.Optional;

import com.sandeeprm.oms.orderservice.controllers.resources.OrderRequestResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductPriceResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductResource;
import com.sandeeprm.oms.orderservice.repositories.domain.Order;
import com.sandeeprm.oms.orderservice.services.exceptions.OrderServiceException;

/**
 * 
 * @author sandeep-r-m
 *
 */
public interface OrderService {

	/**
	 * 
	 * @return
	 * @throws OrderServiceException
	 */
	public List<Order> fetchAllOrders() throws OrderServiceException;

	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws OrderServiceException
	 */
	public Order fetchOrderById(long orderId) throws OrderServiceException;

	/**
	 * 
	 * @param orderId
	 * @throws OrderServiceException
	 */
	public void deleteOrder(long orderId) throws OrderServiceException;

	/**
	 * 
	 * @param newOrderRequest
	 * @param product
	 * @param productInventory
	 * @param productPrice
	 * @return
	 * @throws OrderServiceException
	 */
	public Order processOrder(OrderRequestResource newOrderRequest, Optional<ProductResource> product,
			Optional<ProductInventoryResource> productInventory, Optional<ProductPriceResource> productPrice)
			throws OrderServiceException;

}
