package com.example.ably.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ably.bookmark.entity.BookmarkEntity;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long>, BookmarkRepositoryCustom {

}
