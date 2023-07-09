package com.example.ably.drawer.dto;

import java.util.List;

import com.example.ably.product.dto.ProductDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrawerDetailDto {
	private long count;
	private List<ProductDto> products;

	@Builder
	public DrawerDetailDto(long count, List<ProductDto> products) {
		this.count = count;
		this.products = products;
	}
}
