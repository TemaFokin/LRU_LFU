package com.lruandlfu.demo.dto;

import lombok.Data;

@Data
public class Request {
    private Integer id;
    private Object object;
    private String cachingMethod;
}
