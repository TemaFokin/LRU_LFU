package com.lruandlfu.demo.cache;

import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LFUCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class LFUTest {

    @Mock
    private LFUCache lfuCache = new LFUCache();

    @InjectMocks
    private LFU lfu = new LFU(lfuCache);

    @Test
    void crowdingOutTheFirstObjectFromTheQueue() {
        lfuCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        lfu.caching(firstCachedObject);
        lfu.caching(secondCachedObject);
        lfu.caching(secondCachedObject);
        lfu.caching(thirdCachedObject);

        assertNull(lfuCache.getCacheMap().get(firstCachedObject.getId()));
    }

    @Test
    void checkCacheSize(){
        lfuCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        lfu.caching(firstCachedObject);
        lfu.caching(secondCachedObject);
        lfu.caching(thirdCachedObject);

        assertEquals(1, lfuCache.getCacheMap().size());
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