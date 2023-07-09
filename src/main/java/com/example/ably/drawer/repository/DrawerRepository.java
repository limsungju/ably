package com.example.ably.drawer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ably.drawer.entity.DrawerEntity;

public interface DrawerRepository extends JpaRepository<DrawerEntity, Long>, DrawerRepositoryCustom {
	@Query(
		value = "SELECT d FROM DrawerEntity d JOIN d.member WHERE d.name = :name AND d.member.id = :memberId"
	)
	Optional<DrawerEntity> findOneByNameAndMemberId(
		@Param("name") String name
		, @Param("memberId") Long memberId
	);

	@Query(
		value = "SELECT count(d) FROM DrawerEntity d JOIN d.member WHERE d.isUsed = :isUsed AND d.member.id = :memberId"
	)
	long countByIsUsedAndMemberId(
		@Param("isUsed") boolean isUsed
		, @Param("memberId") Long memberId
	);

	@Query(
		value = "SELECT d FROM DrawerEntity d JOIN d.member WHERE d.id = :drawerId AND d.isUsed = :isUsed AND d.member.id = :memberId"
	)
	Optional<DrawerEntity> findByIdAndUsedAndMemberId(
		@Param("drawerId") Long drawerId
		, @Param("isUsed") boolean isUsed
		, @Param("memberId") Long memberId
	);

	@Query(
		value = "UPDATE DrawerEntity d SET d.isUsed = false WHERE d.id = :drawerId"
	)
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	void deleteById(@Param("drawerId") Long drawerId);
}
