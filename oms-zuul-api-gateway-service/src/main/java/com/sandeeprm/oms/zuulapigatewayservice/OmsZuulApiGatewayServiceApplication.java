package com.sandeeprm.oms.zuulapigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class OmsZuulApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsZuulApiGatewayServiceApplication.class, args);
	}
}
