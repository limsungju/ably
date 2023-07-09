package com.example.ably.drawer.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.ably.bookmark.entity.BookmarkEntity;
import com.example.ably.member.entity.MemberEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "Drawer", schema = "ably")
public class DrawerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DRAWER_ID", nullable = false)
	private Long id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "SORT", nullable = false)
	private Long sort;
	@Column(name="IS_USED", nullable = false)
	private boolean isUsed;
	@Column(name="CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
	@Column(name="UPDATED_AT", nullable = false)
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID", nullable = false)
	private MemberEntity member;

	@OneToMany(mappedBy = "drawer", fetch = FetchType.LAZY)
	private List<BookmarkEntity> bookmarks = new ArrayList<>();

	@Builder(builderMethodName = "createBuilder")
	public DrawerEntity(
		String name, long sort, MemberEntity member
	) {
		LocalDateTime nowDate = LocalDateTime.now();
		this.name = name;
		this.sort = sort;
		this.isUsed = true;
		this.createdAt = nowDate;
		this.updatedAt = nowDate;
		this.member = member;
	}
}
