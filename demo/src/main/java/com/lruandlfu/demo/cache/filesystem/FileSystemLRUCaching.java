package com.lruandlfu.demo.cache.filesystem;

import com.lruandlfu.demo.entities.LRUFileCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileSystemLRUCaching {

    private final LRUFileCache lruFileCache;
    private Map<Integer, String> files = new HashMap<>();
    private Deque<Integer> queue = new LinkedList<>();
    private File cacheDirectory = new File("src/lrucacheddirectory");

    public void caching(Integer key, File file) throws IOException {
        if (cacheDirectory.listFiles().length == lruFileCache.getCapacity()) {
            int last = queue.removeLast();
            File fileToBeDeleted = new File(cacheDirectory.getAbsolutePath() +
                    "/" + files.get(last));
            fileToBeDeleted.delete();
            files.remove(last);
        } else {
            queue.remove(key);
        }
        queue.push(key);
        files.put(key, file.getName());
        File newFile = new File(cacheDirectory.getPath() + "/" + file.getName());
        if (!newFile.exists()) {
            Files.copy(file.toPath(), newFile.toPath());
        }
    }
}
