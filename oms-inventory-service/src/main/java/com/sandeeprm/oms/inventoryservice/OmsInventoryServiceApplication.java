package com.sandeeprm.oms.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.SocketUtils;

@EnableEurekaClient
@SpringBootApplication
public class OmsInventoryServiceApplication {

	public static void main(String[] args) {
		configure();
		SpringApplication.run(OmsInventoryServiceApplication.class, args);
	}

	private static void configure() {
		// Override server.port at runtime
		int port = SocketUtils.findAvailableTcpPort();
		System.setProperty("server.port", port + "");
	}
}
