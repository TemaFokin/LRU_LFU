package com.lruandlfu.demo.entities;

import lombok.Data;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
@Data
public class LRUCache {
    HashMap<Integer, Object> cacheMap = new HashMap<>();
    int capacity;
}
