package com.sandeeprm.oms.catalogservice.controllers.resourcemodel;

import org.springframework.hateoas.ResourceSupport;

import com.sandeeprm.oms.catalogservice.controllers.ProductCatalogController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class ProductResource extends ResourceSupport {

	private Long productId;

	public ProductResource(Long productId) {
		super();
		this.productId = productId;
		add(linkTo(methodOn(ProductCatalogController.class).getProductById(productId)).withSelfRel());
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
