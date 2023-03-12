package com.swansong.productservice.controller;

import com.swansong.productservice.model.Product;
import com.swansong.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Log
public class ProductController {

    private final ProductRepository  productRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        log.info("createProduct("+(product!=null? "name="+product.getName(): "" )+")");
        productRepository.save(product);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> findById(@PathVariable String id) {
        log.info("findById("+id+")");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll() {
        log.info("findAll()");
        return productRepository.findAll();
    }
}
