package com.example.ably.drawer.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrawerDto {
	private long drawerId;
	private String name;
	private long sort;

	@QueryProjection
	public DrawerDto(long drawerId, String name, long sort) {
		this.drawerId = drawerId;
		this.name = name;
		this.sort = sort;
	}
}
