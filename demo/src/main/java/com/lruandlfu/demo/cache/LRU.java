package com.lruandlfu.demo.cache;

import org.springframework.stereotype.Service;

@Service("lru")
public class LRU extends Caching{
    @Override
    String cache() {
        return null;
    }
}
