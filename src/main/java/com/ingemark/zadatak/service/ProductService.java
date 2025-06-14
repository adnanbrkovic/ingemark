package com.ingemark.zadatak.service;

import com.ingemark.zadatak.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product findByCode(String code);

    List<Product> findAll();

    Product save(Product product);

}
