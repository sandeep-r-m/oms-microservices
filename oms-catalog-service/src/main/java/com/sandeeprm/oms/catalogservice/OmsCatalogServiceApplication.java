package com.sandeeprm.oms.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.SocketUtils;

@EnableEurekaClient
@SpringBootApplication
public class OmsCatalogServiceApplication {

	public static void main(String[] args) {
		configure();
		SpringApplication.run(OmsCatalogServiceApplication.class, args);
	}

	private static void configure() {
		// Override server.port at runtime
		int port = SocketUtils.findAvailableTcpPort();
		System.setProperty("server.port", port + "");
	}

}
