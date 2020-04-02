package com.lruandlfu.demo.dto;

import lombok.Data;

import java.io.File;

@Data
public class Response {
    private Integer id;
    private File file;
}
