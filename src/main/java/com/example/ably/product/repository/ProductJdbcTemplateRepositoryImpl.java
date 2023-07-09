package com.example.ably.product.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ably.product.entity.ProductEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductJdbcTemplateRepositoryImpl implements ProductJdbcTemplateRepository {
	private final JdbcTemplate jdbcTemplate;

	@Override
	public void saveAll(List<ProductEntity> productEntities) {
		String sql = "INSERT INTO `product` "
			+ "(`name`\n" +
			"   , `price`\n" +
			"   , `thumbnail`\n" +
			"   , `is_used`\n" +
			"   , `created_at`\n" +
			"   , `updated_at`\n" +
			") " +
			"VALUES (?, ?, ?, ?, ?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ProductEntity product = productEntities.get(i);
				ps.setString(1, product.getName());
				ps.setInt(2, product.getPrice());
				ps.setString(3, product.getThumbnail());
				ps.setBoolean(4, product.isUsed());
				ps.setObject(5, product.getCreatedAt());
				ps.setObject(6, product.getUpdatedAt());
			}

			@Override
			public int getBatchSize() {
				return productEntities.size();
			}
		});
	}
}
