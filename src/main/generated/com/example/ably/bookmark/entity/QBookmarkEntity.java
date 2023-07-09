package com.example.ably.bookmark.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookmarkEntity is a Querydsl query type for BookmarkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmarkEntity extends EntityPathBase<BookmarkEntity> {

    private static final long serialVersionUID = -241384293L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookmarkEntity bookmarkEntity = new QBookmarkEntity("bookmarkEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final com.example.ably.drawer.entity.QDrawerEntity drawer;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.ably.member.entity.QMemberEntity member;

    public final com.example.ably.product.entity.QProductEntity product;

    public QBookmarkEntity(String variable) {
        this(BookmarkEntity.class, forVariable(variable), INITS);
    }

    public QBookmarkEntity(Path<? extends BookmarkEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookmarkEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookmarkEntity(PathMetadata metadata, PathInits inits) {
        this(BookmarkEntity.class, metadata, inits);
    }

    public QBookmarkEntity(Class<? extends BookmarkEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.drawer = inits.isInitialized("drawer") ? new com.example.ably.drawer.entity.QDrawerEntity(forProperty("drawer"), inits.get("drawer")) : null;
        this.member = inits.isInitialized("member") ? new com.example.ably.member.entity.QMemberEntity(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new com.example.ably.product.entity.QProductEntity(forProperty("product")) : null;
    }

}

