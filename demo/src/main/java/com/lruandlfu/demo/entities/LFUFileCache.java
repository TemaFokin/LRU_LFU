package com.lruandlfu.demo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LFUFileCache {
    private int capacity;
}
