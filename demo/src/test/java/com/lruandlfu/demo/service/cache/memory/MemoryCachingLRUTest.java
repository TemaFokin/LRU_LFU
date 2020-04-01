package com.lruandlfu.demo.service.cache.memory;

import com.lruandlfu.demo.service.cache.filesystem.FileSystemLRUCaching;
import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LRUCache;
import com.lruandlfu.demo.entities.LRUFileCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MemoryCachingLRUTest extends MemoryCachingTest{

    @Mock
    private LRUFileCache lruFileCache = new LRUFileCache();

    @Mock
    private FileSystemLRUCaching fileSystemLRUCaching = new FileSystemLRUCaching(lruFileCache);

    @Mock
    private LRUCache lruCache = new LRUCache();

    @InjectMocks
    private MemoryCachingLRU memoryCachingLru = new MemoryCachingLRU(lruCache, fileSystemLRUCaching);

    @Test
    void crowdingOutTheFirstObjectFromTheQueue() throws IOException {
        lruCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        memoryCachingLru.caching(firstCachedObject);
        memoryCachingLru.caching(secondCachedObject);
        memoryCachingLru.caching(secondCachedObject);
        memoryCachingLru.caching(thirdCachedObject);

        assertNull(lruCache.getCacheMap().get(firstCachedObject.getId()));
    }

    @Test
    void checkCacheSize() throws IOException {
        lruCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        memoryCachingLru.caching(firstCachedObject);
        memoryCachingLru.caching(secondCachedObject);
        memoryCachingLru.caching(thirdCachedObject);

        assertEquals(2, lruCache.getCacheMap().size());
    }
}