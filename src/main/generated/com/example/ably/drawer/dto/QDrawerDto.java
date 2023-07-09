package com.example.ably.drawer.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.ably.drawer.dto.QDrawerDto is a Querydsl Projection type for DrawerDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QDrawerDto extends ConstructorExpression<DrawerDto> {

    private static final long serialVersionUID = 191951697L;

    public QDrawerDto(com.querydsl.core.types.Expression<Long> drawerId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Long> sort) {
        super(DrawerDto.class, new Class<?>[]{long.class, String.class, long.class}, drawerId, name, sort);
    }

}

