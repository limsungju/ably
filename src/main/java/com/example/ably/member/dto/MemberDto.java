package com.example.ably.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {
	private String email;

	@Builder
	public MemberDto(String email) {
		this.email = email;
	}
}
