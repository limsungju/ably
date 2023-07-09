package com.example.ably.bookmark.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ably.bookmark.dto.SaveBookmarkDto;
import com.example.ably.bookmark.entity.BookmarkEntity;
import com.example.ably.bookmark.repository.BookmarkRepository;
import com.example.ably.drawer.entity.DrawerEntity;
import com.example.ably.drawer.repository.DrawerRepository;
import com.example.ably.product.entity.ProductEntity;
import com.example.ably.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository bookmarkRepository;
	private final DrawerRepository drawerRepository;
	private final ProductRepository productRepository;

	@Transactional
	public Long saveBookmark(SaveBookmarkDto saveBookmarkDto) {
		DrawerEntity drawer = drawerRepository.findById(saveBookmarkDto.getDrawerId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 서랍 입니다."));

		if (!Objects.equals(drawer.getMember().getId(), saveBookmarkDto.getMemberId())) {
			throw new IllegalArgumentException("서랍의 소유자가 아닙니다.");
		}

		ProductEntity product = productRepository.findById(saveBookmarkDto.getProductId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 입니다."));

		String drawerName = bookmarkRepository.findOneByMemberIdAndProductId(
			saveBookmarkDto.getMemberId()
			, saveBookmarkDto.getProductId()
		);

		if (drawerName != null) {
			throw new IllegalArgumentException(drawerName + " 서랍에 상품이 존재합니다.");
		}

		BookmarkEntity bookmark = bookmarkRepository.save(
			BookmarkEntity.createBuilder()
				.drawer(drawer)
				.member(drawer.getMember())
				.product(product)
				.build()
		);

		return bookmark.getId();
	}

	@Transactional
	public void deleteBookmark(Long bookmarkId, Long memberId) {
		bookmarkRepository.findByIdAndMemberId(bookmarkId, memberId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 북마크 입니다."));

		bookmarkRepository.deleteById(bookmarkId);
	}
}
