package com.ingemark.zadatak.repository;

import com.ingemark.zadatak.domain.entity.ProductEntity;
import com.ingemark.zadatak.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Product findByCode(String code);
}
