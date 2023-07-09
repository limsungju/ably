package com.example.ably.product.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {
	private Long id;
	private String name;
	private String thumbnail;
	private Integer price;

	@QueryProjection
	public ProductDto(Long id, String name, String thumbnail, Integer price) {
		this.id = id;
		this.name = name;
		this.thumbnail = thumbnail;
		this.price = price;
	}
}
