package com.ingemark.zadatak.service.impl;

import com.ingemark.zadatak.domain.entity.ProductEntity;
import com.ingemark.zadatak.domain.model.Product;
import com.ingemark.zadatak.exception.ValidationException;
import com.ingemark.zadatak.mapper.Mapper;
import com.ingemark.zadatak.repository.ProductRepository;
import com.ingemark.zadatak.service.ProductService;
import com.ingemark.zadatak.utilities.MessageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MessageUtils messageUtils;

    public List<Product> findAll(){

        List<ProductEntity> entities = productRepository.findAll();
        return entities.stream()
                .map(Mapper::toProduct)
                .collect(Collectors.toList());

    }

    public Product findByCode(String code){

        Product product = productRepository.findByCode(code);
        if(product == null){
            throw new ValidationException(messageUtils.getMessage("product.code.notexists", new Object[]{code}));
        }
        return product;

    }



    @Transactional
    public Product save(Product product){

        validateProduct(product);
        ProductEntity productEntity = Mapper.toProductEntity(product);
        return Mapper.toProduct(productRepository.save(productEntity));

    }

    private void validateProduct(Product product){

        if (product.getCode() == null) {
            throw new ValidationException(
                    messageUtils.getMessage("product.code.null", null)
            );
        }
        if (product.getCode().length() != 10) {
            throw new ValidationException(
                    messageUtils.getMessage("product.code.length", null)
            );
        }
        if (product.getName() == null || product.getName().isBlank()) {
            throw new ValidationException(
                    messageUtils.getMessage("product.name.null", null)
            );
        }
        if (product.getPriceEur() == null || product.getPriceEur().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException(
                    messageUtils.getMessage("product.priceEur.invalid", null)
            );
        }
        if (product.getIsAvailable() == null) {
            throw new ValidationException(
                    messageUtils.getMessage("product.isAvailable.null", null)
            );
        }

    }


}
