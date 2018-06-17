package com.sandeeprm.oms.zuulapigatewayservice.fallbacks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;

@Component
public class ServiceFallbackProvider implements FallbackProvider {

	@Override
	public String getRoute() {
		return "*"; // Use * for all service-ids. Use route service-id for specific service
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		if (cause instanceof HystrixTimeoutException)
			return response(HttpStatus.GATEWAY_TIMEOUT);

		return response(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ClientHttpResponse response(final HttpStatus httpStatus) {
		return new ClientHttpResponse() {

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("{ \"errorMessage\": \"API Gateway fallback\"}".getBytes());
			}

			@Override
			public String getStatusText() throws IOException {
				return httpStatus.getReasonPhrase();
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return httpStatus;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return httpStatus.value();
			}

			@Override
			public void close() {

			}
		};
	}

}
