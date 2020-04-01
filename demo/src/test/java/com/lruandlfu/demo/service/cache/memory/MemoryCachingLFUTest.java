package com.lruandlfu.demo.service.cache.memory;

import com.lruandlfu.demo.service.cache.filesystem.FileSystemLFUCaching;
import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LFUCache;
import com.lruandlfu.demo.entities.LFUFileCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class MemoryCachingLFUTest extends MemoryCachingTest {

    @Mock
    private LFUFileCache lfuFileCache = new LFUFileCache();

    @Mock
    private FileSystemLFUCaching lfuCaching = new FileSystemLFUCaching(lfuFileCache);

    @Mock
    private LFUCache lfuCache = new LFUCache();

    @InjectMocks
    private MemoryCachingLFU memoryCachingLfu = new MemoryCachingLFU(lfuCaching, lfuCache);

    @Test
    void crowdingOutTheFirstObjectFromTheQueue() throws IOException {
        lfuCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        memoryCachingLfu.caching(firstCachedObject);
        memoryCachingLfu.caching(secondCachedObject);
        memoryCachingLfu.caching(secondCachedObject);
        memoryCachingLfu.caching(thirdCachedObject);

        assertNull(lfuCache.getCacheMap().get(firstCachedObject.getId()));
    }

    @Test
    void checkCacheSize() throws IOException {
        lfuCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();
        doNothing().when(lfuCaching).caching(any(), any());

        memoryCachingLfu.caching(firstCachedObject);
        memoryCachingLfu.caching(secondCachedObject);
        memoryCachingLfu.caching(secondCachedObject);
        memoryCachingLfu.caching(thirdCachedObject);

        assertEquals(2, lfuCache.getCacheMap().size());
    }
}