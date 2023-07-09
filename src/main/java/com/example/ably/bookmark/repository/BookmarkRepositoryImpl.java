package com.example.ably.bookmark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.ably.bookmark.entity.BookmarkEntity;
import com.example.ably.bookmark.entity.QBookmarkEntity;
import com.example.ably.drawer.entity.QDrawerEntity;
import com.example.ably.member.entity.QMemberEntity;
import com.example.ably.product.dto.ProductDto;
import com.example.ably.product.entity.QProductEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	private final QBookmarkEntity bookmark = QBookmarkEntity.bookmarkEntity;
	private final QDrawerEntity drawer = QDrawerEntity.drawerEntity;
	private final QProductEntity product = QProductEntity.productEntity;
	private final QMemberEntity member = QMemberEntity.memberEntity;

	@Override
	public String findOneByMemberIdAndProductId(long memberId, long productId) {
		return jpaQueryFactory
			.select(drawer.name)
			.from(bookmark)
			.join(bookmark.member, member)
			.join(bookmark.product, product)
			.join(bookmark.drawer, drawer)
			.where(
				member.id.eq(memberId)
				, product.id.eq(productId)
				, product.isUsed.eq(true)
				, drawer.isUsed.eq(true)
			)
			.fetchOne();
	}

	@Override
	public Optional<BookmarkEntity> findByIdAndMemberId(long bookmarkId, long memberId) {
		return Optional.ofNullable(
			jpaQueryFactory
				.selectFrom(bookmark)
				.join(bookmark.member, member)
				.where(
					bookmark.id.eq(bookmarkId)
					, member.id.eq(memberId)
				)
				.fetchOne()
		);
	}

	@Override
	public List<ProductDto> findAllByMemberId(Pageable pageable, long memberId, long drawerId, Long bookmarkId) {
		return jpaQueryFactory
			.select(
				Projections.constructor(ProductDto.class,
					product.id
					, product.name
					, product.thumbnail
					, product.price
				)
			)
			.from(bookmark)
			.join(bookmark.member, member)
			.join(bookmark.product, product)
			.join(bookmark.drawer, drawer)
			.where(
				member.id.eq(memberId)
				, product.isUsed.eq(true)
				, drawer.isUsed.eq(true)
				, this.ltBookmarkId(bookmarkId)
			)
			.orderBy(bookmark.id.desc())
			.offset(this.zeroOffset(bookmarkId, pageable.getOffset()))
			.limit(pageable.getPageSize())
			.fetch();
	}

	@Override
	public long countByDrawerId(long drawerId) {
		return jpaQueryFactory
			.select(bookmark.id.count())
			.from(bookmark)
			.join(bookmark.drawer, drawer)
			.where(drawer.isUsed.eq(true))
			.fetchOne();
	}

	private long zeroOffset(long offset, Long id) {
		if (id == null || id == 0L) {
			return offset;
		}
		return 0L;
	}

	private BooleanExpression ltBookmarkId(Long bookmarkId) {
		if (bookmarkId == null || bookmarkId == 0L) {
			return null;
		}
		return bookmark.id.lt(bookmarkId);
	}
}
