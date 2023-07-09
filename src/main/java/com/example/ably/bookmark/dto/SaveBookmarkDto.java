package com.example.ably.bookmark.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveBookmarkDto {
	@NotNull
	@Positive
	private Long drawerId;
	@NotNull
	@Positive
	private Long productId;
	@NotNull
	@Positive
	private Long memberId;

	@Builder
	public SaveBookmarkDto(Long drawerId, Long productId, Long memberId) {
		this.drawerId = drawerId;
		this.productId = productId;
		this.memberId = memberId;
	}
}
