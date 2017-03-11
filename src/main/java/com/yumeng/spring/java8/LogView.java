package com.yumeng.spring.java8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yumeng on 2017/3/11.
 */
public class LogView {
    private long lastTimeFileSize = 0;  //上次文件大小

    public void realtimeShowLog(File logFile) throws FileNotFoundException {
        final RandomAccessFile randomAccessFile = new RandomAccessFile(logFile, "rw");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(() -> {
            try {
                randomAccessFile.seek(lastTimeFileSize);
                String temp = "";
                while ((temp = randomAccessFile.readLine()) != null) {
                    System.out.println(temp);
                }
                lastTimeFileSize = randomAccessFile.length();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }, 0, 2, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        File file = new File("/Users/yumeng/Documents/test.txt");

        LogView logView = new LogView();
        try {
            logView.realtimeShowLog(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
