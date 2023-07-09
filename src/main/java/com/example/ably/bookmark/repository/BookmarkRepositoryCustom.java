package com.example.ably.bookmark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.example.ably.bookmark.entity.BookmarkEntity;
import com.example.ably.product.dto.ProductDto;

public interface BookmarkRepositoryCustom {
	String findOneByMemberIdAndProductId(long memberId, long productId);
	Optional<BookmarkEntity> findByIdAndMemberId(long bookmarkId, long memberId);
	List<ProductDto> findAllByMemberId(Pageable pageable, long memberId, long drawerId, Long bookmarkId);
	long countByDrawerId(long drawerId);
}
