package com.example.ably.drawer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrawerEntity is a Querydsl query type for DrawerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrawerEntity extends EntityPathBase<DrawerEntity> {

    private static final long serialVersionUID = -1622228847L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDrawerEntity drawerEntity = new QDrawerEntity("drawerEntity");

    public final ListPath<com.example.ably.bookmark.entity.BookmarkEntity, com.example.ably.bookmark.entity.QBookmarkEntity> bookmarks = this.<com.example.ably.bookmark.entity.BookmarkEntity, com.example.ably.bookmark.entity.QBookmarkEntity>createList("bookmarks", com.example.ably.bookmark.entity.BookmarkEntity.class, com.example.ably.bookmark.entity.QBookmarkEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isUsed = createBoolean("isUsed");

    public final com.example.ably.member.entity.QMemberEntity member;

    public final StringPath name = createString("name");

    public final NumberPath<Long> sort = createNumber("sort", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QDrawerEntity(String variable) {
        this(DrawerEntity.class, forVariable(variable), INITS);
    }

    public QDrawerEntity(Path<? extends DrawerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDrawerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDrawerEntity(PathMetadata metadata, PathInits inits) {
        this(DrawerEntity.class, metadata, inits);
    }

    public QDrawerEntity(Class<? extends DrawerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.ably.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}

