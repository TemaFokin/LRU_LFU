package com.lruandlfu.demo.service.cache.filesystem;

import com.lruandlfu.demo.entities.CachedObject;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class FileSystemCachingTest {

    private static final File TEST_FILE = new File("E:\\WORK\\LRU_LFU\\demo\\src\\test\\java\\com\\lruandlfu\\demo\\testfiles\\TestFile");
    private static final File TEST_FILE_2 = new File("E:\\WORK\\LRU_LFU\\demo\\src\\test\\java\\com\\lruandlfu\\demo\\testfiles\\TestFile2");
    private static final File TEST_FILE_3 = new File("E:\\WORK\\LRU_LFU\\demo\\src\\test\\java\\com\\lruandlfu\\demo\\testfiles\\TestFile3");

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
