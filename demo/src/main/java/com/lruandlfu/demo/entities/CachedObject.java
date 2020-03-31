package com.lruandlfu.demo.entities;

import lombok.Data;

import java.io.File;

@Data
public class CachedObject {
    private Integer id;
    private File file;
    private String cachingMethod;
}
