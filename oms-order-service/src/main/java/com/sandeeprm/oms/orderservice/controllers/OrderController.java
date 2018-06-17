package com.sandeeprm.oms.orderservice.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.sandeeprm.oms.orderservice.controllers.feignclients.CatalogServiceClient;
import com.sandeeprm.oms.orderservice.controllers.feignclients.InventoryServiceClient;
import com.sandeeprm.oms.orderservice.controllers.feignclients.PricingServiceClient;
import com.sandeeprm.oms.orderservice.controllers.resourcemodel.OrderRequestResource;
import com.sandeeprm.oms.orderservice.controllers.resourcemodel.OrderResource;
import com.sandeeprm.oms.orderservice.entities.Order;
import com.sandeeprm.oms.orderservice.entities.Product;
import com.sandeeprm.oms.orderservice.entities.ProductInventory;
import com.sandeeprm.oms.orderservice.entities.ProductPrice;
import com.sandeeprm.oms.orderservice.services.OrderService;
import com.sandeeprm.oms.orderservice.services.exceptions.OrderServiceException;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private EurekaClient eurekaClient; // Using Eureka client (not needed when using Feign client)

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Autowired
	private CatalogServiceClient productCatalogServiceClient; // Using Feign Client

	@Autowired
	private InventoryServiceClient inventoryServiceClient; // Using Feign Client

	@Autowired
	private PricingServiceClient pricingServiceClient;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping
	public ResponseEntity<List<Order>> getOrders() {
		List<Order> orderList = orderService.getOrders();
		if (orderList.size() <= 0) {
			return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Optional<Order> order = orderService.getOrderById(id);
		if (!order.isPresent()) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<OrderResource> save(@RequestBody OrderRequestResource newOrderRequest) {

		if (null == newOrderRequest)
			return new ResponseEntity<OrderResource>(HttpStatus.BAD_REQUEST);

		// Get Product details
		Optional<Product> product = getProductV2(newOrderRequest.getProductId());
		if (!product.isPresent())
			return new ResponseEntity<OrderResource>(HttpStatus.BAD_REQUEST);

		// Get Inventory details
		Optional<ProductInventory> productInventory = getProductInventoryV2(newOrderRequest.getProductId());
		if (!productInventory.isPresent())
			return new ResponseEntity<OrderResource>(HttpStatus.BAD_REQUEST);

		// Get Price details
		Optional<ProductPrice> productPrice = getPriceByProductId(newOrderRequest.getProductId());
		if (!productPrice.isPresent())
			return new ResponseEntity<OrderResource>(HttpStatus.BAD_REQUEST);

		// Try to save order
		try {
			Long newOrderId = orderService.save(newOrderRequest, product.get(), productInventory.get(),
					productPrice.get());

			// Success
			OrderResource orderResource = new OrderResource(newOrderId, HttpStatus.CREATED.name());
			return new ResponseEntity<OrderResource>(orderResource, HttpStatus.CREATED);

		} catch (OrderServiceException e) {
			OrderResource orderResource = new OrderResource(-1L, e.getMessage());
			return new ResponseEntity<OrderResource>(orderResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private Optional<ProductPrice> getPriceByProductId(Long productId) {
		ProductPrice productPrice = pricingServiceClient.getPriceByProductId(productId);
		return Optional.ofNullable(productPrice);
	}

	/**
	 * This is using Feign Client
	 * 
	 * @param productId
	 * @return
	 */
	private Optional<Product> getProductV2(Long productId) {
		Product p = productCatalogServiceClient.getProductById(productId);
		return Optional.ofNullable(p);
	}

	/**
	 * This is using Feign Client
	 * 
	 * @param productId
	 * @return
	 */
	private Optional<ProductInventory> getProductInventoryV2(Long productId) {
		ProductInventory productInventory = inventoryServiceClient.getInventoryByProductId(productId);
		return Optional.ofNullable(productInventory);
	}

	/**
	 * This is using Eureka Client to get service hostname/IP address and port to
	 * build URL
	 * 
	 * @return
	 */
	private String getCatalogServiceBaseURL() {
		com.netflix.discovery.shared.Application application = eurekaClient.getApplication("omscatalogservice");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String baseURL = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
		return baseURL;
	}

	/**
	 * This is using Eureka Client to get service hostname/IP address and port to
	 * build URL
	 * 
	 * @return
	 */
	private String getInventoryServiceBaseURL() {
		Application application = eurekaClient.getApplication("omsinventoryservice");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String baseURL = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort();
		return baseURL;
	}

	/**
	 * This is using Spring RestTemplate to call the service
	 * 
	 * @param productId
	 * @return
	 */
	private Optional<Product> getProduct(Long productId) {
		String serviceURL = UriComponentsBuilder.fromHttpUrl(getCatalogServiceBaseURL()).path("/products/")
				.path(productId.toString()).toUriString();
		logger.info("Calling Product Catalog Service using URL " + serviceURL);
		ResponseEntity<Product> responseEntity = restTemplateBuilder.build().getForEntity(serviceURL, Product.class);
		if (HttpStatus.OK == responseEntity.getStatusCode()) {
			return Optional.ofNullable(responseEntity.getBody());
		}
		return Optional.empty();
	}

	/**
	 * This is using Spring RestTemplate to call the service
	 * 
	 * @param productId
	 * @return
	 */
	private Optional<ProductInventory> getProductInventory(Long productId) {
		String serviceURL = UriComponentsBuilder.fromHttpUrl(getInventoryServiceBaseURL()).path("/inventories/")
				.path(productId.toString()).toUriString();
		logger.info("Calling Product Inventory Service using URL " + serviceURL);
		ResponseEntity<ProductInventory> responseEntity = restTemplateBuilder.build().getForEntity(serviceURL,
				ProductInventory.class);
		if (HttpStatus.OK == responseEntity.getStatusCode()) {
			return Optional.ofNullable(responseEntity.getBody());
		}
		return Optional.empty();
	}

}
