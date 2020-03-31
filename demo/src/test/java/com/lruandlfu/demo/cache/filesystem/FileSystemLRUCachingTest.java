package com.lruandlfu.demo.cache.filesystem;

import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LRUFileCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemLRUCachingTest extends FileSystemCachingTest{

    @Mock
    private final LRUFileCache lruFileCache = new LRUFileCache();

    @InjectMocks
    private FileSystemLRUCaching fileSystemLRUCaching = new FileSystemLRUCaching(lruFileCache);

    @Test
    void crowdingOutTheFirstObjectFromTheQueue() throws IOException {
        lruFileCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        fileSystemLRUCaching.caching(firstCachedObject.getId(), firstCachedObject.getFile());
        fileSystemLRUCaching.caching(secondCachedObject.getId(), secondCachedObject.getFile());
        fileSystemLRUCaching.caching(secondCachedObject.getId(), secondCachedObject.getFile());
        fileSystemLRUCaching.caching(thirdCachedObject.getId(), thirdCachedObject.getFile());
        File firstFile = new File("src/lrucacheddirectory", firstCachedObject.getFile().getName());

        assertFalse(firstFile.exists());

        File secondFile = new File("src/lrucacheddirectory/TestFile2");
        secondFile.delete();
        File thirdFile = new File("src/lrucacheddirectory/TestFile3");
        thirdFile.delete();
    }
}