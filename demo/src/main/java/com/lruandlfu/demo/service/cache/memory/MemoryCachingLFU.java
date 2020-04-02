package com.lruandlfu.demo.service.cache.memory;

import com.lruandlfu.demo.service.cache.filesystem.FileSystemLFUCaching;
import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LFUCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service("lfu")
@RequiredArgsConstructor
public class MemoryCachingLFU {

    private final FileSystemLFUCaching fileSystemLFUCaching;
    private final LFUCache lfuCache;
    private Map<Integer, Integer> hitCounter = new ConcurrentHashMap<>();

    public void caching(CachedObject cachedObject) throws IOException {
        if (!lfuCache.getCacheMap().containsKey(cachedObject.getId())) {
            if (hitCounter.size() == lfuCache.getCapacity()) {
                List<Integer> listWithHitCounterValues = new ArrayList<>();
                for (Map.Entry entry : hitCounter.entrySet()) {
                    listWithHitCounterValues.add((Integer) entry.getValue());
                }
                List<Integer> keys = listWithKeys(Collections.min(listWithHitCounterValues));
                for (Integer key : keys) {
                    fileSystemLFUCaching.caching(key, lfuCache.getCacheMap().get(key));
                    hitCounter.remove(key);
                    lfuCache.getCacheMap().remove(key);
                }
            }
            lfuCache.getCacheMap().put(cachedObject.getId(), cachedObject.getFile());
            hitCounter.put(cachedObject.getId(), 1);
        } else {
            hitCounter.put(cachedObject.getId(), hitCounter.get(cachedObject.getId()) + 1);
        }
    }

    private List<Integer> listWithKeys(Integer value) {
        List<Integer> listWithKeys = new ArrayList<>();
        for (Map.Entry entry : hitCounter.entrySet()) {
            if (entry.getValue() == value) {
                listWithKeys.add((Integer) entry.getKey());
            }
        }
        return listWithKeys;
    }
}
