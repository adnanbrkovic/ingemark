package com.ingemark.zadatak;

import static org.assertj.core.api.Assertions.assertThat;

import com.ingemark.zadatak.domain.entity.ProductEntity;
import com.ingemark.zadatak.domain.model.Product;
import com.ingemark.zadatak.mapper.Mapper;
import com.ingemark.zadatak.repository.ProductRepository;
import com.ingemark.zadatak.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(properties = "spring.profiles.active=test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void testFindByCode() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode("AAAAAAAAAA");
        productEntity.setName("Test Product");
        productEntity.setPriceEur(BigDecimal.valueOf(100));
        productEntity.setPriceUsd(BigDecimal.valueOf(110));
        productEntity.setIsAvailable(true);

        when(productRepository.findByCode("AAAAAAAAAA")).thenReturn(productEntity);

        Product product = productService.findByCode("AAAAAAAAAA");
        assertThat(product).isNotNull();
        assertThat(product.getCode()).isEqualTo("AAAAAAAAAA");
    }

    @Test
    void testFindAll() {
        ProductEntity p1 = new ProductEntity();
        p1.setCode("AAAAAAAAAA");
        ProductEntity p2 = new ProductEntity();
        p2.setCode("AAAAAAAAAB");

        List<ProductEntity> entities = Arrays.asList(p1, p2);
        when(productRepository.findAll()).thenReturn(entities);

        List<Product> products = productService.findAll();

        assertThat(products).hasSize(2);
        assertThat(products.get(0).getCode()).isEqualTo("AAAAAAAAAA");
        assertThat(products.get(1).getCode()).isEqualTo("AAAAAAAAAB");
    }

    @Test
    void testSave() {
        Product product = new Product();
        product.setCode("AAAAAAAAAC");
        product.setName("New Product");
        product.setPriceEur(BigDecimal.valueOf(100));
        product.setIsAvailable(true);

        ProductEntity entityToSave = Mapper.toProductEntity(product);

        ProductEntity savedEntity = new ProductEntity();
        savedEntity.setCode("AAAAAAAAAC");
        savedEntity.setName("New Product");
        product.setPriceEur(BigDecimal.valueOf(100));
        savedEntity.setId(1L);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedEntity);

        Product savedProduct = productService.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
    }
}


