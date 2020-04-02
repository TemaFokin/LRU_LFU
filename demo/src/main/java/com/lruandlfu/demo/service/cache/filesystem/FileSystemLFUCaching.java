package com.lruandlfu.demo.service.cache.filesystem;

import com.lruandlfu.demo.entities.LFUFileCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class FileSystemLFUCaching {

    private File cacheDirectory = new File("src/lfucacheddirectory");
    private final LFUFileCache lfuFileCache;
    private Map<Integer, Integer> hitCounter = new ConcurrentHashMap<>();

    public void caching(Integer key, File file) throws IOException {
        boolean fileAvailability = false;
        if (cacheDirectory.listFiles().length == lfuFileCache.getCapacity()) {
            List<Integer> listWithHitCounterValues = new ArrayList<>();
            for (Map.Entry entry : hitCounter.entrySet()) {
                listWithHitCounterValues.add((Integer) entry.getValue());
            }
            List<Integer> keys = listWithKeys(Collections.min(listWithHitCounterValues));
            for (Integer keyToBeDeleted : keys) {
                File fileToBeDeleted = new File(cacheDirectory.getPath() + "/" +
                        lfuFileCache.getFiles().get(keyToBeDeleted));
                hitCounter.remove(keyToBeDeleted);
                fileToBeDeleted.delete();
            }
        }
        for (File cachedFile : cacheDirectory.listFiles()) {
            if (cachedFile.getName().equals(file.getName())) {
                hitCounter.put(key, hitCounter.get(key) + 1);
                fileAvailability = true;
            }
        }
        if (!fileAvailability) {
            hitCounter.put(key, 1);
            File newFile = new File(cacheDirectory.getPath() + "/" + file.getName());
            if (!newFile.exists()) {
                Files.copy(file.toPath(), newFile.toPath());
            }
            lfuFileCache.getFiles().put(key, file.getName());
        }
    }

    private List<Integer> listWithKeys(Integer value) {
        List<Integer> listWithKeys = new ArrayList<>();
        for (Map.Entry entry : hitCounter.entrySet()) {
            if (entry.getValue() == value) {
                listWithKeys.add((Integer) entry.getKey());
            }
        }
        return listWithKeys;
    }
}
