package com.thang.redisperformance.service;

import org.redisson.api.RedissonReactiveClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import javax.annotation.PostConstruct;

@Service
public class ProductVisitService {

    @Autowired
    private RedissonReactiveClient client;

    private Sinks.Many<Integer> sink;

    public ProductVisitService() {
        this.sink = Sinks.many().unicast().onBackpressureBuffer();
    }

    @PostConstruct
    private void init() {
        this.sink
    }
}
