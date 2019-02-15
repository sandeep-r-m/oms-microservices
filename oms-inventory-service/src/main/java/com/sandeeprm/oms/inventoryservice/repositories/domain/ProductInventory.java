package com.sandeeprm.oms.inventoryservice.repositories.domain;

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
@Table(name = "OMS_PRODUCT_INVENTORY")
public class ProductInventory {

	@Id
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "QUANTITY", updatable = true, nullable = false)
	private Integer quantity;

	@Column(name = "DT_CREATED", updatable = false, nullable = false)
	private LocalDateTime dateCreated;

	@Column(name = "DT_UPDATED", updatable = true, nullable = false)
	private LocalDateTime dateUpdated;

	public static ProductInventory newInstance(Long productId, Integer quantity) {
		ProductInventory pi = new ProductInventory();
		pi.setProductId(productId);
		pi.setQuantity(quantity);
		return pi;
	}

}
