package com.example.ably.drawer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveDrawerDto {
	@NotBlank
	@Length(max = 30)
	private String name;
	@NotNull
	@Positive
	private Long memberId;

	@Builder
	public SaveDrawerDto(String name, Long memberId) {
		this.name = name;
		this.memberId = memberId;
	}
}
