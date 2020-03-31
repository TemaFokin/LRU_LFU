package com.lruandlfu.demo.qualifier;

import com.lruandlfu.demo.cache.memory.Caching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Qualifier {

    @Autowired
    private Map<String, Caching> beans = new HashMap<>();

    public Caching getCacheStrategy(String string){
        return beans.get(string);
    }
}
