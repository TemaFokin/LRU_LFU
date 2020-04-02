package com.lruandlfu.demo.service.getter;

import com.lruandlfu.demo.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class FileGetter {

    private final LFUCache lfuCache;
    private final LRUCache lruCache;
    private final LFUFileCache lfuFileCache;
    private final LRUFileCache lruFileCache;

    public CachedObject getFile(CachedObject cachedObject){
        if (cachedObject.getCachingMethod().equals("lfu")) {
           cachedObject.setFile(getLfuFile(cachedObject.getId()));
        }
        if (cachedObject.getCachingMethod().equals("lru")) {
            cachedObject.setFile(getLruFile(cachedObject.getId()));
        }
        return cachedObject;
    }

    private File getLfuFile(int id) {
        File lfuCacheDirectory = new File("src/lfucacheddirectory");
        File foundFile;
        if (lfuCache.getCacheMap().containsKey(id)){
            foundFile = lfuCache.getCacheMap().get(id);
        } else {
            foundFile = new File(lfuCacheDirectory, lfuFileCache.getFiles().get(id));
        }
        return foundFile;
    }

    private File getLruFile(int id) {
        File lruCacheDirectory = new File("src/lrucacheddirectory");
        File foundFile;
        if (lruCache.getCacheMap().containsKey(id)){
            foundFile = lruCache.getCacheMap().get(id);
        } else {
            foundFile = new File(lruCacheDirectory, lruFileCache.getFiles().get(id));
        }
        return foundFile;
    }
}
