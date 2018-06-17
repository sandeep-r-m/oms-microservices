package com.sandeeprm.oms.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.util.SocketUtils;

@EnableEurekaClient
@SpringBootApplication
public class OmsOrderServiceApplication {

	public static void main(String[] args) {
		configure();
		SpringApplication.run(OmsOrderServiceApplication.class, args);
	}

	private static void configure() {
		// Override server.port at runtime
		int port = SocketUtils.findAvailableTcpPort();
		System.setProperty("server.port", port + "");
	}

}
