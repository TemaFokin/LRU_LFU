package com.lruandlfu.demo.entities;

import lombok.Data;

@Data
public class CacheParams {
    private int memoryLFUCacheSize;
    private int memoryLRUCacheSize;
    private int fileSystemLFUCacheSize;
    private int fileSystemLRUCacheSize;
}
