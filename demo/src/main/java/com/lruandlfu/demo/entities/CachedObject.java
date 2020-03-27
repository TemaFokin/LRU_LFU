package com.lruandlfu.demo.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class CachedObject {
    private Integer id;
    private Object object;
    private String cachingMethod;
}
