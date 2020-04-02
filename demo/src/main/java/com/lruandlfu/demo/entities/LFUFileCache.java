package com.lruandlfu.demo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class LFUFileCache {
    private Map<Integer, String> files = new HashMap<>();
    private int capacity;
}
