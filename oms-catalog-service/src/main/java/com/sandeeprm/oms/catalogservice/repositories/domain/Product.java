package com.sandeeprm.oms.catalogservice.repositories.domain;

import java.time.Clock;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "OMS_PRODUCT_DETAILS")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "NAME", length = 50, updatable = true, nullable = false)
	private String name;

	@Column(name = "DESC", length = 255, updatable = true, nullable = false)
	private String desc;

	@Column(name = "DT_CREATED", updatable = false, nullable = false)
	private LocalDateTime dateCreated;

	@Column(name = "DT_UPDATED", updatable = true, nullable = false)
	private LocalDateTime dateUpdated;

	public static Product newInstance(Long productId, String name, String desc) {
		Product p = new Product();
		p.setProductId(productId);
		p.setName(name);
		p.setDesc(desc);
		p.setDateCreated(LocalDateTime.now(Clock.systemUTC()));
		p.setDateUpdated(LocalDateTime.now(Clock.systemUTC()));
		return p;
	}

}
