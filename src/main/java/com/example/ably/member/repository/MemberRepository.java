package com.example.ably.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ably.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findOneByEmail(String email);
	Optional<MemberEntity> findOneByEmailAndPassword(String email, String password);
}
