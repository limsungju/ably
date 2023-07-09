package com.example.ably.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ably.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
