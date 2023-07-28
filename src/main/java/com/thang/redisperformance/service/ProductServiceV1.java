package com.thang.redisperformance.service;

import com.thang.redisperformance.entity.Product;
import com.thang.redisperformance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceV1 {

    @Autowired
    private ProductRepository repository;

    public Mono<Product> getProduct(int id) {
        return repository.findById(id);
    }

    public Mono<Product> updateProduct(int id, Mono<Product> productMono) {
        return repository.findById(id)
                .flatMap(p -> productMono.doOnNext(product -> product.setId(id)))
                .flatMap(p -> repository.save(p));
    }
}
