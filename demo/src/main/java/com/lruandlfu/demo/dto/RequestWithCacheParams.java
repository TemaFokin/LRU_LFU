package com.lruandlfu.demo.dto;

import lombok.Data;

@Data
public class RequestWithCacheParams {
    private int memoryLFUCacheSize;
    private int memoryLRUCacheSize;
    private int fileSystemLFUCacheSize;
    private int fileSystemLRUCacheSize;
}
