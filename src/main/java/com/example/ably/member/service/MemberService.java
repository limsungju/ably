package com.example.ably.member.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ably.member.dto.LoginDto;
import com.example.ably.member.dto.MemberDto;
import com.example.ably.member.dto.SaveMemberDto;
import com.example.ably.member.entity.MemberEntity;
import com.example.ably.member.repository.MemberRepository;
import com.example.ably.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final ProductService productService;
	@Transactional
	public Long saveMember(SaveMemberDto saveMemberDto) {
		Optional<MemberEntity> findMember = memberRepository.findOneByEmail(saveMemberDto.getEmail());

		if (findMember.isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}

		MemberEntity member = memberRepository.save(
			MemberEntity.createBuilder()
				.email(saveMemberDto.getEmail())
				.password(saveMemberDto.getPassword())
				.build()
		);
		return member.getId();
	}

	@Transactional(readOnly = true)
	public Long login(LoginDto loginDto) {
		MemberEntity member = memberRepository.findOneByEmailAndPassword(
			loginDto.getEmail()
			, loginDto.getPassword()
		).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

		return member.getId();
	}

	@Transactional(readOnly = true)
	public MemberDto findDetail(Long memberId) {
		MemberEntity member = memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

		return MemberDto.builder().email(member.getEmail()).build();
	}
}
