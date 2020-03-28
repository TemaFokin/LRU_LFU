package com.lruandlfu.demo.cache;

import com.lruandlfu.demo.entities.LRUCache;
import com.lruandlfu.demo.entities.CachedObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("lru")
@RequiredArgsConstructor
public class LRU extends Caching {

    private final LRUCache LRUCache;
    private Deque<Integer> queue = new LinkedList<>();

    @Override
    public void caching(CachedObject cachedObject) {
        if (!LRUCache.getCacheMap().containsKey(cachedObject.getId())) {
            if (queue.size() == LRUCache.getCapacity()) {
                //todo implement adding to the database
                int last = queue.removeLast();
                LRUCache.getCacheMap().remove(last);
            }
        } else {
            queue.remove(cachedObject.getId());
        }
        queue.push(cachedObject.getId());
        LRUCache.getCacheMap().put(cachedObject.getId(), cachedObject.getObject());
    }
}
