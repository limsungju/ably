package com.example.ably.product.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsDto {
	private List<ProductDto> products;

	@Builder
	public ProductsDto(List<ProductDto> products) {
		this.products = products;
	}
}
