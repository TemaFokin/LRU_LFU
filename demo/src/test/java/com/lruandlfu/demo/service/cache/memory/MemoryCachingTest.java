package com.lruandlfu.demo.service.cache.memory;

import com.lruandlfu.demo.entities.CachedObject;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class MemoryCachingTest {


    private static final File TEST_FILE = new File("com/lruandlfu/demo/testfiles/TestFile");
    private static final File TEST_FILE_2 = new File("com/lruandlfu/demo/testfiles/TestFile2");
    private static final File TEST_FILE_3 = new File("com/lruandlfu/demo/testfiles/TestFile3");

    CachedObject firstCachedObject(){
        CachedObject cachedObject = new CachedObject();
        cachedObject.setId(1);
        cachedObject.setFile(TEST_FILE);
        return cachedObject;
    }

    CachedObject secondCachedObject(){
        CachedObject cachedObject = new CachedObject();
        cachedObject.setId(2);
        cachedObject.setFile(TEST_FILE_2);
        return cachedObject;
    }

    CachedObject thirdCachedObject(){
        CachedObject cachedObject = new CachedObject();
        cachedObject.setId(3);
        cachedObject.setFile(TEST_FILE_3);
        return cachedObject;
    }
}
