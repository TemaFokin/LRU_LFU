package com.lruandlfu.demo.cache;

import org.springframework.stereotype.Service;

@Service("lfu")
public class LFU extends Caching{
    @Override
    String cache() {
        return null;
    }
}
