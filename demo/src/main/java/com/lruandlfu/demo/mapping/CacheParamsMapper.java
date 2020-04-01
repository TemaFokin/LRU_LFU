package com.lruandlfu.demo.mapping;

import com.lruandlfu.demo.dto.RequestWithCacheParams;
import com.lruandlfu.demo.entities.CacheParams;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CacheParamsMapper {
    CacheParamsMapper MAPPER = Mappers.getMapper(CacheParamsMapper.class);

    @Mapping(source = "memoryLFUCacheSize", target = "memoryLFUCacheSize")
    @Mapping(source = "memoryLRUCacheSize", target = "memoryLRUCacheSize")
    @Mapping(source = "fileSystemLFUCacheSize", target = "fileSystemLFUCacheSize")
    @Mapping(source = "fileSystemLRUCacheSize", target = "fileSystemLRUCacheSize")
    CacheParams requestWithCacheParamsToCacheParams(RequestWithCacheParams requestWithCacheParams);
}
