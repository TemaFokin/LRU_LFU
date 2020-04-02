package com.lruandlfu.demo.mapping;

import com.lruandlfu.demo.dto.Request;
import com.lruandlfu.demo.dto.Response;
import com.lruandlfu.demo.entities.CachedObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CachedObjectMapperTest {

    private static final String CACHING_METHOD = "CachingMethod";
    private static final int ID = 1;
    private static final File OBJECT = new File("com/lruandlfu/demo/testfiles/TestFile");

    @Test
    void requestToCachedObject() {
        Request request = request();

        CachedObject cachedObject = CachedObjectMapper.MAPPER.requestToCachedObject(request);

        assertAll(
                () -> assertEquals(request.getId(), cachedObject.getId()),
                () -> assertEquals(request.getCachingMethod(), cachedObject.getCachingMethod()),
                () -> assertEquals(request.getFile(), cachedObject.getFile())
        );
    }

    @Test
    void cachedObjectToResponse() {
        CachedObject cachedObject = cachedObject();

        Response response = CachedObjectMapper.MAPPER.cachedObjectToResponse(cachedObject);

        assertAll(
                () -> assertEquals(cachedObject.getId(), response.getId()),
                () -> assertEquals(cachedObject.getFile(), response.getFile())
        );
    }

    private Request request(){
        Request request = new Request();
        request.setId(ID);
        request.setCachingMethod(CACHING_METHOD);
        request.setFile(OBJECT);
        return request;
    }

    private CachedObject cachedObject(){
        CachedObject cachedObject = new CachedObject();
        cachedObject.setFile(OBJECT);
        cachedObject.setId(ID);
        cachedObject.setCachingMethod(CACHING_METHOD);
        return cachedObject;
    }
}