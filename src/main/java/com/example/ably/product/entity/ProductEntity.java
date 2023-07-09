package com.example.ably.product.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Product", schema = "ably")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID", nullable = false)
	private Long id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "THUMBNAIL", nullable = false)
	private String thumbnail;
	@Column(name = "PRICE", nullable = false)
	private Integer price;
	@Column(name="IS_USED", nullable = false)
	private boolean isUsed;
	@Column(name="CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
	@Column(name="UPDATED_AT", nullable = false)
	private LocalDateTime updatedAt;

	@Builder(builderMethodName = "createBuilder")
	public ProductEntity(String name, String thumbnail, Integer price) {
		LocalDateTime nowDate = LocalDateTime.now();
		this.name = name;
		this.thumbnail = thumbnail;
		this.price = price;
		this.isUsed = true;
		this.createdAt = nowDate;
		this.updatedAt = nowDate;
	}
}
