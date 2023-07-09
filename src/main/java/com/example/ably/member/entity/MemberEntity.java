package com.example.ably.member.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.ably.drawer.entity.DrawerEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "Member", schema = "ably")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_ID", nullable = false)
	private Long id;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name="IS_USED", nullable = false)
	private boolean isUsed;
	@Column(name="CREATED_AT", nullable = false)
	private LocalDateTime createdAt;
	@Column(name="UPDATED_AT", nullable = false)
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<DrawerEntity> drawers = new ArrayList<>();

	@Builder(builderMethodName = "createBuilder")
	public MemberEntity(String email, String password) {
		LocalDateTime nowDate = LocalDateTime.now();
		this.email = email;
		this.password = password;
		this.isUsed = true;
		this.createdAt = nowDate;
		this.updatedAt = nowDate;
	}
}
