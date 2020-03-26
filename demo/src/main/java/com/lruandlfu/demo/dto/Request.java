package com.lruandlfu.demo.dto;

import lombok.Data;

@Data
public class Request {
    private Object object;
    private String cachingMethod;
}
