package com.lruandlfu.demo.service.getter;

import com.lruandlfu.demo.entities.*;
import com.lruandlfu.demo.service.cache.memory.MemoryCachingLFU;
import com.lruandlfu.demo.service.cache.memory.MemoryCachingLRU;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileGetter {

    private final LFUCache lfuCache;
    private final LRUCache lruCache;
    private final LFUFileCache lfuFileCache;
    private final LRUFileCache lruFileCache;

    public CachedObject getFile(CachedObject cachedObject) throws IOException {
        if (cachedObject.getCachingMethod().equals("lfu")) {
           cachedObject.setFile(getLfuFile(cachedObject));
        }
        if (cachedObject.getCachingMethod().equals("lru")) {
            cachedObject.setFile(getLruFile(cachedObject));
        }
        return cachedObject;
    }

    private File getLfuFile(CachedObject cachedObject) {
        File lfuCacheDirectory = new File("src/lfucacheddirectory");
        if (lfuCache.getCacheMap().containsKey(cachedObject.getId())){
            cachedObject.setFile(lfuCache.getCacheMap().get(cachedObject.getId()));
        } else {
            File foundFile = new File(lfuCacheDirectory, lfuFileCache.getFiles().get(cachedObject.getId()));
            cachedObject.setFile(foundFile);
        }
        return cachedObject.getFile();
    }

    private File getLruFile(CachedObject cachedObject) {
        File lruCacheDirectory = new File("src/lrucacheddirectory");
        if (lruCache.getCacheMap().containsKey(cachedObject.getId())){
            cachedObject.setFile(lruCache.getCacheMap().get(cachedObject.getId()));
        } else {
            File foundFile = new File(lruCacheDirectory, lruFileCache.getFiles().get(cachedObject.getId()));
            cachedObject.setFile(foundFile);
        }
        return cachedObject.getFile();
    }
}
