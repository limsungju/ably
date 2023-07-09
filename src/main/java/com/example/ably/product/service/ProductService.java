package com.example.ably.product.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ably.product.entity.ProductEntity;
import com.example.ably.product.repository.ProductJdbcTemplateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductJdbcTemplateRepository productJdbcTemplateRepository;

	@Transactional
	public void saveAll() {
		productJdbcTemplateRepository.saveAll(this.setDummyData());
	}

	private List<ProductEntity> setDummyData() {
		String csvFile = "dummy_product.csv";
		String line;
		String csvSplitBy = ",";
		List<ProductEntity> productEntities = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(csvSplitBy);

				productEntities.add(
					ProductEntity.createBuilder()
						.name(data[0])
						.thumbnail(data[1])
						.price(Integer.parseInt(data[2]))
						.build()
				);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productEntities;
	}
}
