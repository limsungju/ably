package com.example.ably.drawer.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrawersDto {
	private List<DrawerDto> drawers;

	@Builder
	public DrawersDto(List<DrawerDto> drawers) {
		this.drawers = drawers;
	}
}
