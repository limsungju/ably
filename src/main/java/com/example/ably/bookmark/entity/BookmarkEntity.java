package com.example.ably.bookmark.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ably.drawer.entity.DrawerEntity;
import com.example.ably.member.entity.MemberEntity;
import com.example.ably.product.entity.ProductEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "Bookmark", schema = "ably")
public class BookmarkEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKMARK_ID", nullable = false)
	private Long id;
	@Column(name="CREATED_AT", nullable = false)
	private LocalDateTime createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DRAWER_ID", nullable = false)
	private DrawerEntity drawer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private ProductEntity product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID", nullable = false)
	private MemberEntity member;

	@Builder(builderMethodName = "createBuilder")
	public BookmarkEntity(DrawerEntity drawer, ProductEntity product, MemberEntity member
	) {
		this.drawer = drawer;
		this.product = product;
		this.member = member;
		this.createdAt = LocalDateTime.now();
	}
}
