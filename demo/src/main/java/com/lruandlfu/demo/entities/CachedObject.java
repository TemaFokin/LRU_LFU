package com.lruandlfu.demo.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class CachedObject {
    private Object object;
    private String cachingMethod;
}
