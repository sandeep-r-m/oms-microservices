package com.sandeeprm.oms.springbootadmingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class OmsSpringbootAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsSpringbootAdminServiceApplication.class, args);
	}
}
