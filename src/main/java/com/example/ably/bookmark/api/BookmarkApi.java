package com.example.ably.bookmark.api;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ably.bookmark.dto.SaveBookmarkDto;
import com.example.ably.bookmark.service.BookmarkService;
import com.example.ably.drawer.dto.DrawersDto;
import com.example.ably.drawer.dto.SaveDrawerDto;
import com.example.ably.product.dto.ProductsDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="찜")
@RestController
@RequiredArgsConstructor
@RequestMapping(value={"/api/bookmarks"})
public class BookmarkApi {
	private final BookmarkService bookmarkService;

	@Operation(summary = "", description = "찜 등록 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201",
			description = "찜 등록 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = Long.class)
				)
			}
		)
	})
	@PostMapping("")
	public ResponseEntity<Long> saveBookmark(@RequestBody @Valid SaveBookmarkDto saveBookmarkDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookmarkService.saveBookmark(saveBookmarkDto));
	}

	@Operation(summary = "", description = "찜 삭제 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "204",
			description = "찜 삭제 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = Void.class)
				)
			}
		)
	})
	@DeleteMapping("/{bookmarkId}/members/{memberId}")
	public ResponseEntity<Void> deleteBookmark(
		@PathVariable(name="bookmarkId") @NotNull @Positive Long bookmarkId
		, @PathVariable(name="memberId") @NotNull @Positive Long memberId
	) {
		bookmarkService.deleteBookmark(bookmarkId, memberId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
