package com.melodymatebackend.config.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class CacheService {

    @Cacheable(cacheNames = "cacheStore", key = "#name")
    public String getCache(String name) throws InterruptedException {
        sleep(3000);
        return name;
    }
}
