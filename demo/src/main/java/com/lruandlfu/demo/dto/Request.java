package com.lruandlfu.demo.dto;

import lombok.Data;

import java.io.File;

@Data
public class Request {
    private Integer id;
    private File file;
    private String cachingMethod;
}
