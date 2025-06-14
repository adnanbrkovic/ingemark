package com.ingemark.zadatak.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable {

    private Long id;
    private String code;
    private String name;
    private BigDecimal priceEur;
    private BigDecimal priceUsd;
    private Boolean isAvailable;


}
