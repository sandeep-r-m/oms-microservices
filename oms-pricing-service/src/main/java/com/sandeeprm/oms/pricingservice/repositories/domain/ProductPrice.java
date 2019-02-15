package com.sandeeprm.oms.pricingservice.repositories.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "OMS_PRODUCT_PRICE")
public class ProductPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRODUCT_ID", updatable = false)
	private Long productId;

	@Column(name = "PRICE", updatable = true, nullable = false)
	private BigDecimal price;

	@Column(name = "DT_CREATED", updatable = false, nullable = false)
	private LocalDateTime dateCreated;

	@Column(name = "DT_UPDATED", updatable = true, nullable = false)
	private LocalDateTime dateUpdated;

	public static ProductPrice newInstance(Long productId, BigDecimal price) {
		ProductPrice pp = new ProductPrice();
		pp.setProductId(productId);
		pp.setPrice(price);
		pp.setDateCreated(LocalDateTime.now(Clock.systemUTC()));
		pp.setDateUpdated(LocalDateTime.now(Clock.systemUTC()));
		return pp;
	}

}
