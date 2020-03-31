package com.lruandlfu.demo.rest;

import com.lruandlfu.demo.cache.memory.Caching;
import com.lruandlfu.demo.dto.Request;
import com.lruandlfu.demo.dto.Response;
import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.mapping.CachedObjectMapper;
import com.lruandlfu.demo.qualifier.Qualifier;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/lfu_lru")
@RequiredArgsConstructor
public class RestController {

    @Autowired
    private Qualifier qualifier;

    @PostMapping(value = "/cache")
    @ResponseBody
    public Response cache(@NonNull @RequestBody Request request){
        CachedObject cachedObject = CachedObjectMapper.MAPPER.requestToCachedObject(request);
        Caching caching = qualifier.getCacheStrategy(cachedObject.getCachingMethod());
        return CachedObjectMapper.MAPPER.cachedObjectToResponse(cachedObject);
    }

}
