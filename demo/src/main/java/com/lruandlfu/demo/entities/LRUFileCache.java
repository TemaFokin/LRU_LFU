package com.lruandlfu.demo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class LRUFileCache {
    private int capacity;
}
