package com.example.ably.drawer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ably.bookmark.repository.BookmarkRepository;
import com.example.ably.drawer.dto.DrawerDetailDto;
import com.example.ably.drawer.dto.DrawersDto;
import com.example.ably.drawer.dto.SaveDrawerDto;
import com.example.ably.drawer.entity.DrawerEntity;
import com.example.ably.drawer.repository.DrawerRepository;
import com.example.ably.member.entity.MemberEntity;
import com.example.ably.member.repository.MemberRepository;
import com.example.ably.product.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrawerService {
	private final DrawerRepository drawerRepository;
	private final MemberRepository memberRepository;
	private final BookmarkRepository bookmarkRepository;
	@Transactional
	public Long saveDrawer(SaveDrawerDto saveDrawerDto) {
		MemberEntity member = memberRepository.findById(saveDrawerDto.getMemberId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));

		Optional<DrawerEntity> findDrawer = drawerRepository.findOneByNameAndMemberId(
			saveDrawerDto.getName()
			, saveDrawerDto.getMemberId()
		);

		if (findDrawer.isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 서랍 입니다.");
		}

		long maxSort = drawerRepository.countByIsUsedAndMemberId(true, saveDrawerDto.getMemberId());

		DrawerEntity drawer = drawerRepository.save(
			DrawerEntity.createBuilder()
				.name(saveDrawerDto.getName())
				.member(member)
				.sort(maxSort + 1)
				.build()
		);

		return drawer.getId();
	}

	@Transactional
	public void deleteDrawer(Long drawerId, Long memberId) {
		drawerRepository.findByIdAndUsedAndMemberId(drawerId, true, memberId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 서랍 입니다."));

		drawerRepository.deleteById(drawerId);
	}

	@Transactional(readOnly = true)
	public DrawersDto findDrawers(Pageable pageable, long memberId, Long drawerId) {
		return DrawersDto.builder()
			.drawers(drawerRepository.findAllByMemberId(pageable, memberId, drawerId))
			.build();
	}

	@Transactional(readOnly = true)
	public DrawerDetailDto findDetail(Pageable pageable, Long bookmarkId, long drawerId, long memberId) {
		List<ProductDto> products = new ArrayList<>();
		long count = bookmarkRepository.countByDrawerId(drawerId);
		if (count > 0 ) {
			products = bookmarkRepository.findAllByMemberId(pageable, memberId, drawerId, bookmarkId);
		}

		return DrawerDetailDto.builder()
			.count(count)
			.products(products)
			.build();
	}
}
