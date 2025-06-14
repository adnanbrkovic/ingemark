package com.ingemark.zadatak.controller;

import com.ingemark.zadatak.domain.model.Product;
import com.ingemark.zadatak.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/")
@Log4j2
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("find/{code}")
    public ResponseEntity<Product> findByCode(@PathVariable String code){

        log.info("Find product request received for code {}", code);
        return ResponseEntity.ok(productService.findByCode(code));
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Product>> findAll(){

        log.info("Find product list request received");
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("save")
    public ResponseEntity<Product> save(@RequestBody  Product product){

        log.info("Save product request received - product = {}", product);
        return ResponseEntity.ok(productService.save(product));
    }


}
