package com.example.ably.drawer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.ably.drawer.dto.DrawerDto;

public interface DrawerRepositoryCustom {
	List<DrawerDto> findAllByMemberId(Pageable pageable, long memberId, Long drawerId);
}
