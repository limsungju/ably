package com.example.ably.drawer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.ably.drawer.dto.DrawerDto;
import com.example.ably.drawer.entity.QDrawerEntity;
import com.example.ably.member.entity.QMemberEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DrawerRepositoryImpl implements DrawerRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	private final QDrawerEntity drawer = QDrawerEntity.drawerEntity;
	private final QMemberEntity member = QMemberEntity.memberEntity;

	@Override
	public List<DrawerDto> findAllByMemberId(Pageable pageable, long memberId, Long drawerId) {
		return jpaQueryFactory
			.select(
				Projections.constructor(DrawerDto.class,
					drawer.id
					, drawer.name
					, drawer.sort
				)
			)
			.from(drawer)
			.join(drawer.member, member)
			.where(
				member.id.eq(memberId)
				, drawer.isUsed.eq(true)
				, this.ltDrawerId(drawerId)
			)
			.orderBy(drawer.sort.desc())
			.offset(this.zeroOffset(drawerId, pageable.getOffset()))
			.limit(pageable.getPageSize())
			.fetch();
	}

	private long zeroOffset(long offset, Long id) {
		if (id == null || id == 0L) {
			return offset;
		}
		return 0L;
	}
	private BooleanExpression ltDrawerId(Long drawerId) {
		if (drawerId == null || drawerId == 0L) {
			return null;
		}
		return drawer.id.lt(drawerId);
	}
}
