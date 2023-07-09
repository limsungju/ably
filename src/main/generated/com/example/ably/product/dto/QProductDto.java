package com.example.ably.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.ably.product.dto.QProductDto is a Querydsl Projection type for ProductDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductDto extends ConstructorExpression<ProductDto> {

    private static final long serialVersionUID = -1642944275L;

    public QProductDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> thumbnail, com.querydsl.core.types.Expression<Integer> price) {
        super(ProductDto.class, new Class<?>[]{long.class, String.class, String.class, int.class}, id, name, thumbnail, price);
    }

}

