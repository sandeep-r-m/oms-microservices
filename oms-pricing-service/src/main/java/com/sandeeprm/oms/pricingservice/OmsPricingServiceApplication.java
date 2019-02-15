package com.sandeeprm.oms.pricingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OmsPricingServiceApplication {

	public static void main(String[] args) {
		// configure();
		SpringApplication.run(OmsPricingServiceApplication.class, args);
	}

	/*
	 * private static void configure() { int port =
	 * SocketUtils.findAvailableTcpPort(); System.setProperty("server.port", port +
	 * ""); }
	 */
}
