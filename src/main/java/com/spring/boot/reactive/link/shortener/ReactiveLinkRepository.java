package com.spring.boot.reactive.link.shortener;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class ReactiveLinkRepository implements LinkRepository {

    private final ReactiveRedisOperations<String, String> operations;

    public ReactiveLinkRepository(ReactiveRedisOperations<String, String> operations) {
        this.operations = operations;
    }

    @Override
    public Mono<Link> save(Link link) {
        return operations.opsForValue()
                         .set(link.getKey(), link.getOiginalLink())
                         .map(result -> link);
    }

    @Override
    public Mono<Link> findByKey(String key) {
        return operations.opsForValue()
                         .get(key)
                         .map(result -> new Link(result, key));
    }
}