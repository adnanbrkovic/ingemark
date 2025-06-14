package com.ingemark.zadatak.mapper;

import com.ingemark.zadatak.domain.entity.ProductEntity;
import com.ingemark.zadatak.domain.model.Product;

public class Mapper {

    public static Product toProduct(ProductEntity productEntity){
        if (productEntity == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setCode(productEntity.getCode());
        product.setName(productEntity.getName());
        product.setPriceEur(productEntity.getPriceEur());
        product.setPriceUsd(productEntity.getPriceUsd());
        product.setIsAvailable(productEntity.getIsAvailable());

        return product;
    }

    public static ProductEntity toProductEntity(Product product){
        if (product == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        product.setId(product.getId());
        product.setCode(product.getCode());
        product.setName(product.getName());
        product.setPriceEur(product.getPriceEur());
        product.setPriceUsd(product.getPriceUsd());
        product.setIsAvailable(product.getIsAvailable());

        return productEntity;
    }
}
