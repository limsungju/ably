package com.example.ably.product.repository;

import java.util.List;

import com.example.ably.product.entity.ProductEntity;

public interface ProductJdbcTemplateRepository {
	void saveAll(List<ProductEntity> productEntities);
}
