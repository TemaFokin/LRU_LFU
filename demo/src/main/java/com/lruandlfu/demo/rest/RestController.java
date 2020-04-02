package com.lruandlfu.demo.rest;

import com.lruandlfu.demo.dto.Request;
import com.lruandlfu.demo.dto.RequestWithCacheParams;
import com.lruandlfu.demo.dto.Response;
import com.lruandlfu.demo.entities.*;
import com.lruandlfu.demo.mapping.CacheParamsMapper;
import com.lruandlfu.demo.mapping.CachedObjectMapper;
import com.lruandlfu.demo.service.cache.memory.MemoryCachingLFU;
import com.lruandlfu.demo.service.cache.memory.MemoryCachingLRU;
import com.lruandlfu.demo.service.getter.FileGetter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/lfu_lru")
@AllArgsConstructor
public class RestController {

    private final FileGetter fileGetter;
    private final LFUCache lfuCache;
    private final LRUCache lruCache;
    private final LRUFileCache lruFileCache;
    private final LFUFileCache lfuFileCache;
    private final MemoryCachingLRU memoryCachingLRU;
    private final MemoryCachingLFU memoryCachingLFU;

    @PostMapping(value = "/cache")
    @ResponseBody
    public void cache(@NonNull @RequestBody Request request) throws IOException {
        CachedObject cachedObject = CachedObjectMapper.MAPPER.requestToCachedObject(request);
        if (cachedObject.getCachingMethod().equals("lfu")){
            memoryCachingLFU.caching(cachedObject);
        }
        if (cachedObject.getCachingMethod().equals("lru")){
            memoryCachingLRU.caching(cachedObject);
        }
    }

    @GetMapping(value = "/get_file")
    @ResponseBody
    public Response getFile(@NonNull @RequestBody Request request) throws IOException {
        CachedObject cachedObject = fileGetter.getFile(CachedObjectMapper.MAPPER.requestToCachedObject(request));
        return CachedObjectMapper.MAPPER.cachedObjectToResponse(cachedObject);
    }

    @PostMapping(value = "/set_cache_size")
    public void setCacheSize(@NonNull @RequestBody RequestWithCacheParams requestWithCacheParams){
        CacheParams cacheParams = CacheParamsMapper.MAPPER.requestWithCacheParamsToCacheParams(requestWithCacheParams);
        lfuCache.setCapacity(cacheParams.getMemoryLFUCacheSize());
        lruCache.setCapacity(cacheParams.getMemoryLRUCacheSize());
        lruFileCache.setCapacity(cacheParams.getFileSystemLRUCacheSize());
        lfuFileCache.setCapacity(cacheParams.getFileSystemLFUCacheSize());
    }
}
