package com.sandeeprm.oms.orderservice.controllers.resources;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResource extends ResourceSupport {

	private Long productId;

	private String name;

	private String desc;

	private LocalDateTime dateCreated;

	private LocalDateTime dateUpdated;

	public static ProductResource newInstance(Long productId, String name, String desc, LocalDateTime dateCreated,
			LocalDateTime dateUpdated) {
		ProductResource productResource = new ProductResource();
		productResource.setProductId(productId);
		productResource.setName(name);
		productResource.setDesc(desc);
		productResource.setDateCreated(dateCreated);
		productResource.setDateUpdated(dateUpdated);
		return productResource;
	}

}
