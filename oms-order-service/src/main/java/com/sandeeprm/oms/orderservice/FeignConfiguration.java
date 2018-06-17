package com.sandeeprm.oms.orderservice;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
@EnableCircuitBreaker
public class FeignConfiguration {

}
