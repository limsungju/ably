package com.example.ably.member.api;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ably.drawer.dto.SaveDrawerDto;
import com.example.ably.member.dto.LoginDto;
import com.example.ably.member.dto.MemberDto;
import com.example.ably.member.dto.SaveMemberDto;
import com.example.ably.member.service.MemberService;
import com.example.ably.product.dto.ProductsDto;
import com.example.ably.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="회원")
@RestController
@RequiredArgsConstructor
@RequestMapping(value={"/api/members"})
public class MemberApi {
	private final MemberService memberService;
	private final ProductService productService;

	@Operation(summary = "", description = "회원 등록 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201",
			description = "회원 등록 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = Long.class)
				)
			}
		)
	})
	@PostMapping("")
	public ResponseEntity<Long> saveMember(@RequestBody @Valid SaveMemberDto saveMemberDto) {
		Long memberId = memberService.saveMember(saveMemberDto);
		productService.saveAll();
		return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
	}

	@Operation(summary = "", description = "로그인 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "로그인 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = Long.class)
				)
			}
		)
	})
	@PostMapping("/login")
	public ResponseEntity<Long> login(@RequestBody @Valid LoginDto loginDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.login(loginDto));
	}

	@Operation(summary = "", description = "회원 상세 조회 요청")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "회원 상세 조회 요청 성공",
			content = {
				@Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					schema = @Schema(implementation = MemberDto.class)
				)
			}
		)
	})
	@GetMapping("/{memberId}/detail")
	public ResponseEntity<MemberDto> findMemberDetail(@PathVariable(name="memberId") @NotNull @Positive Long memberId) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(memberService.findDetail(memberId));
	}
}
