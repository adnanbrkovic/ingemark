package com.ingemark.zadatak.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Product", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", allocationSize = 1)
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(name = "price_eur", nullable = false)
    private BigDecimal priceEur;

    @Column(name = "price_usd", nullable = false)
    private BigDecimal priceUsd;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

}


