package com.lruandlfu.demo.cache;

import com.lruandlfu.demo.entities.LRUCache;
import com.lruandlfu.demo.entities.CachedObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("lru")
@RequiredArgsConstructor
public class LRU extends Caching{

    private final LRUCache LRUCache;
    private static Deque<CachedObject> queue = new LinkedList<>();

    @Override
    public void caching(CachedObject cachedObject) {
        if (!LRUCache.getCacheMap().containsKey(cachedObject.getId())){
            if (queue.size()== LRUCache.getCapacity()){
                //todo implement adding to the database
                CachedObject last = queue.removeLast();
                LRUCache.getCacheMap().remove(last.getId());
            }
        } else {
            queue.remove(cachedObject);
        }
        queue.push(cachedObject);
        LRUCache.getCacheMap().put(cachedObject.getId(), cachedObject.getObject());
    }
}
