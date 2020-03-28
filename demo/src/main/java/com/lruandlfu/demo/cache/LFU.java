package com.lruandlfu.demo.cache;

import com.lruandlfu.demo.entities.CachedObject;
import com.lruandlfu.demo.entities.LFUCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("lfu")
@RequiredArgsConstructor
public class LFU extends Caching{

    private final LFUCache lfuCache;
    private Map<Integer, Integer> hitCounter = new HashMap<>();


    @Override
    public void caching(CachedObject cachedObject) {
        if (!lfuCache.getCacheMap().containsKey(cachedObject.getId())){
            if (hitCounter.size() == lfuCache.getCapacity()){
                List<Integer> listWithHitCounterValues = new ArrayList<>();
                for (Map.Entry entry : hitCounter.entrySet()){
                    listWithHitCounterValues.add((Integer) entry.getValue());
                }
                List<Integer> keys = listWithKeys(Collections.min(listWithHitCounterValues));
                for (Integer key : keys){
                    //todo implement adding to the database
                    hitCounter.remove(key);
                    lfuCache.getCacheMap().remove(key);
                }
            }
            lfuCache.getCacheMap().put(cachedObject.getId(), cachedObject.getObject());
            hitCounter.put(cachedObject.getId(), 1);
        } else {
            hitCounter.put(cachedObject.getId(), hitCounter.get(cachedObject.getId())+1);
        }
    }

    private List<Integer> listWithKeys(Integer value){
        List<Integer> listWithKeys = new ArrayList<>();
        for (Map.Entry entry : hitCounter.entrySet()){
            if (entry.getValue() == value){
                listWithKeys.add((Integer) entry.getKey());
            }
        }
        return listWithKeys;
    }
}
