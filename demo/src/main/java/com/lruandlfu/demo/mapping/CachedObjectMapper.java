package com.lruandlfu.demo.mapping;

import com.lruandlfu.demo.dto.Request;
import com.lruandlfu.demo.dto.Response;
import com.lruandlfu.demo.entities.CachedObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CachedObjectMapper {
    CachedObjectMapper MAPPER = Mappers.getMapper(CachedObjectMapper.class);

    @Mapping(source = "id", target="id")
    @Mapping(source = "file", target = "file")
    @Mapping(source = "cachingMethod", target = "cachingMethod")
    CachedObject requestToCachedObject(Request request);

    @Mapping(source = "id", target="id")
    @Mapping(source = "file", target = "file")
    Response cachedObjectToResponse(CachedObject cachedObject);
}
