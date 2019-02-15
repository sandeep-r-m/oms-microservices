package com.sandeeprm.oms.orderservice.controllers.common;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductInventoryResource;
import com.sandeeprm.oms.orderservice.controllers.resources.ProductResource;

@Component
public class ServiceCallerRestTemplateClient {

	private static final Logger logger = LoggerFactory.getLogger(ServiceCallerRestTemplateClient.class);

	@Autowired
	private EurekaClient eurekaClient; // Using Eureka client (not needed when using Feign client)

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	/**
	 * This is using Eureka Client to get service hostname/IP address and port to
	 * build URL
	 * 
	 * @return
	 */
	public String getCatalogServiceBaseURL() {
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
	public String getInventoryServiceBaseURL() {
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
	public Optional<ProductResource> getProduct(Long productId) {
		String serviceURL = UriComponentsBuilder.fromHttpUrl(getCatalogServiceBaseURL()).path("/products/")
				.path(productId.toString()).toUriString();
		logger.info("Calling Product Catalog Service using URL " + serviceURL);
		ResponseEntity<ProductResource> responseEntity = restTemplateBuilder.build().getForEntity(serviceURL,
				ProductResource.class);
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
	public Optional<ProductInventoryResource> getProductInventory(Long productId) {
		String serviceURL = UriComponentsBuilder.fromHttpUrl(getInventoryServiceBaseURL()).path("/inventories/")
				.path(productId.toString()).toUriString();
		logger.info("Calling Product Inventory Service using URL " + serviceURL);
		ResponseEntity<ProductInventoryResource> responseEntity = restTemplateBuilder.build().getForEntity(serviceURL,
				ProductInventoryResource.class);
		if (HttpStatus.OK == responseEntity.getStatusCode()) {
			return Optional.ofNullable(responseEntity.getBody());
		}
		return Optional.empty();
	}

}
