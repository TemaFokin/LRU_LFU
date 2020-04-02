package com.lruandlfu.demo.service.cache.memory;

import com.lruandlfu.demo.service.cache.filesystem.FileSystemLRUCaching;
import com.lruandlfu.demo.entities.LRUCache;
import com.lruandlfu.demo.entities.CachedObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("lru")
@RequiredArgsConstructor
public class MemoryCachingLRU {

    private final LRUCache lruCache;
    private Deque<Integer> queue = new LinkedList<>();
    private final FileSystemLRUCaching fileSystemLRUCaching;

    public void caching(CachedObject cachedObject) throws IOException {
        if (!lruCache.getCacheMap().containsKey(cachedObject.getId())) {
            if (queue.size() == lruCache.getCapacity()) {
                int last = queue.removeLast();
                fileSystemLRUCaching.caching(last, lruCache.getCacheMap().get(last));
                lruCache.getCacheMap().remove(last);
            }
        } else {
            queue.remove(cachedObject.getId());
        }
        queue.push(cachedObject.getId());
        lruCache.getCacheMap().put(cachedObject.getId(), cachedObject.getFile());
    }
}
