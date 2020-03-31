package com.lruandlfu.demo.cache.filesystem;

import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LFUFileCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemLFUCachingTest extends FileSystemCachingTest{

    @Mock
    private final LFUFileCache lfuFileCache = new LFUFileCache();

    @InjectMocks
    private FileSystemLFUCaching fileSystemLFUCaching = new FileSystemLFUCaching(lfuFileCache);

    @Test
    void crowdingOutTheFirstObjectFromTheQueue() throws IOException {
        lfuFileCache.setCapacity(2);
        CachedObject firstCachedObject = firstCachedObject();
        CachedObject secondCachedObject = secondCachedObject();
        CachedObject thirdCachedObject = thirdCachedObject();

        fileSystemLFUCaching.caching(firstCachedObject.getId(), firstCachedObject.getFile());
        fileSystemLFUCaching.caching(secondCachedObject.getId(), secondCachedObject.getFile());
        fileSystemLFUCaching.caching(secondCachedObject.getId(), secondCachedObject.getFile());
        fileSystemLFUCaching.caching(thirdCachedObject.getId(), thirdCachedObject.getFile());
        File firstFile = new File("src/lfucacheddirectory", firstCachedObject.getFile().getName());

        assertFalse(firstFile.exists());

        File secondFile = new File("src/lfucacheddirectory/TestFile2");
        secondFile.delete();
        File thirdFile = new File("src/lfucacheddirectory/TestFile3");
        thirdFile.delete();
    }

}