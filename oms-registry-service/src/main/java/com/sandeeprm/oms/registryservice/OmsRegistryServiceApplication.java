package com.sandeeprm.oms.registryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OmsRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsRegistryServiceApplication.class, args);
	}
}
