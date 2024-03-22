package com.melodymatebackend.config.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheType {
    CACHE_STORE("cacheStore", 5 * 60, 10000);

    private final String cacheName;
    private final int expireAfterWrite;
    private final int maximumSize;
}
