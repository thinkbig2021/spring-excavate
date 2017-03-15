package com.yumeng.spring.java8;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Created by yumeng on 2017/3/11.
 */
public class FileMoniter {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("/Users/yumeng/Documents/test/test.txt");
        System.out.println(file.getParent());
        Path path = Paths.get(file.getParent());
        WatchService watchService = path.getFileSystem().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.OVERFLOW,StandardWatchEventKinds.ENTRY_DELETE);
        while (true) {
            System.out.println("=========");
            WatchKey key = watchService.take();
            List<WatchEvent<?>> watchEventList = key.pollEvents();
            for (WatchEvent<?> event : watchEventList) {
                System.out.println("["+file.getParent()+"/"+event.context()+"]文件发生了["+event.kind()+"]事件");
                if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                    System.out.println("file has changed");
                }
                if(event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                    System.out.println("file has create");
                }
                if(event.kind().equals(StandardWatchEventKinds.OVERFLOW)) {
                    System.out.println("file has OVERFLOW");
                }
            }
            key.reset();

        }

    }
}
