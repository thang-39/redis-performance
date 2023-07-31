package com.thang.redisperformance.service;

import com.thang.redisperformance.entity.Product;
import com.thang.redisperformance.repository.ProductRepository;
import com.thang.redisperformance.service.util.CacheTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceV2 {

    @Autowired
    private CacheTemplate<Integer, Product> cacheTemplate;

    @Autowired
    private ProductVisitService visitService;

    public Mono<Product> getProduct(int id) {
        return this.cacheTemplate.get(id)
                .doFirst(() -> this.visitService.addVisit(id));
    }

    public Mono<Product> updateProduct(int id, Mono<Product> productMono) {
        return productMono
                .flatMap(p -> this.cacheTemplate.update(id, p));
    }

    public Mono<Void> deleteProduct(int id) {
        return this.cacheTemplate.delete(id);
    }

}
