package com.lruandlfu.demo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;

@Data
@Component
public class LFUCache {
    private HashMap<Integer, File> cacheMap = new HashMap<>();
    int capacity;
}
