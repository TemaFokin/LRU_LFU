package com.lruandlfu.demo.cache;

import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LRUCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LRUTest {

    @Mock
    private LRUCache lruCache = new LRUCache();

    @InjectMocks
    private LRU lru = new LRU(lruCache);

    @Test
    void crowdingOutTheFirstObjectFromTheQueue() {
        lruCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        lru.caching(firstCachedObject);
        lru.caching(secondCachedObject);
        lru.caching(secondCachedObject);
        lru.caching(thirdCachedObject);

        assertNull(lruCache.getCacheMap().get(firstCachedObject.getId()));
    }

    @Test
    void checkCacheSize(){
        lruCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        lru.caching(firstCachedObject);
        lru.caching(secondCachedObject);
        lru.caching(thirdCachedObject);

        assertEquals(2, lruCache.getCacheMap().size());
    }

    private CachedObject firstCachedObject(){
        CachedObject cachedObject = new CachedObject();
        cachedObject.setId(1);
        cachedObject.setObject("First");
        return cachedObject;
    }

    private CachedObject secondCachedObject(){
        CachedObject cachedObject = new CachedObject();
        cachedObject.setId(2);
        cachedObject.setObject("Second");
        return cachedObject;
    }

    private CachedObject thirdCachedObject(){
        CachedObject cachedObject = new CachedObject();
        cachedObject.setId(3);
        cachedObject.setObject("Third");
        return cachedObject;
    }
}