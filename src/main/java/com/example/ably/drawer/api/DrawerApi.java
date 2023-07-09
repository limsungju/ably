package com.example.ably.drawer.api;

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

import com.example.ably.drawer.dto.DrawerDetailDto;
import com.example.ably.drawer.dto.DrawersDto;
import com.example.ably.drawer.dto.SaveDrawerDto;
import com.example.ably.drawer.service.DrawerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="서랍")
@RestController
@RequiredArgsConstructor
@RequestMapping(value={"/api/drawers"})
public class DrawerApi {
	private final DrawerService drawerService;

	@Operation(summary = "", description = "서랍 등록 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201",
			description = "서랍 등록 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = Long.class)
				)
			}
		)
	})
	@PostMapping("")
	public ResponseEntity<Long> saveDrawer(@RequestBody @Valid SaveDrawerDto saveDrawerDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(drawerService.saveDrawer(saveDrawerDto));
	}

	@Operation(summary = "", description = "서랍 삭제 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "204",
			description = "서랍 삭제 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = Void.class)
				)
			}
		)
	})
	@DeleteMapping("/{drawerId}/members/{memberId}")
	public ResponseEntity<Void> deleteDrawer(
		@PathVariable(name="drawerId") @NotNull @Positive Long drawerId
		, @PathVariable(name="memberId") @NotNull @Positive Long memberId
	) {
		drawerService.deleteDrawer(drawerId, memberId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(summary = "", description = "서랍 목록 조회 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "서랍 목록 조회 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = DrawersDto.class)
				)
			}
		)
	})
	@GetMapping("")
	public ResponseEntity<DrawersDto> findDrawers(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") @Max(1_000) int size,
		@RequestParam(defaultValue = "0") Long drawerId,
		@RequestParam @NotNull @Positive long memberId
	) {
		Pageable pageable = PageRequest.of(page, size);

		return ResponseEntity.status(HttpStatus.OK)
			.body(drawerService.findDrawers(pageable, memberId, drawerId));
	}

	@Operation(summary = "", description = "찜 목록 조회 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "찜 목록 조회 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = DrawerDetailDto.class)
				)
			}
		)
	})
	@GetMapping("/{drawerId}/detail")
	public ResponseEntity<DrawerDetailDto> findDetail(
		@PathVariable @NotNull @Positive long drawerId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") @Max(1_000) int size,
		@RequestParam(defaultValue = "0") Long bookmarkId,
		@RequestParam @NotNull @Positive long memberId
	) {
		Pageable pageable = PageRequest.of(page, size);

		return ResponseEntity.status(HttpStatus.OK)
			.body(drawerService.findDetail(pageable, bookmarkId, drawerId, memberId));
	}
}
