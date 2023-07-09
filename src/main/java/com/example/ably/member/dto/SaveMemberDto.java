package com.example.ably.member.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveMemberDto {
	@NotBlank
	@Length(max = 20)
	private String email;
	@NotBlank
	@Length(max = 20)
	private String password;

	@Builder
	public SaveMemberDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
