package com.yumeng.spring.java8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by yumeng on 2017/3/11.
 */
public class LogSvr {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public void logMsg(File logFile, String mesInfo) throws IOException {

        if (logFile == null) {
            throw new IllegalArgumentException("logfile can not null");
        }
        Writer writer = new FileWriter(logFile, true);
        writer.write(format.format(new Date()) + "\t" + mesInfo + "\n");
        writer.flush();
    }

    public static void main(String[] args) {
        LogSvr logsvr = new LogSvr();
        File file = new File("/Users/yumeng/Documents/test.txt");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(() -> {
            try {
                logsvr.logMsg(file, " 99bill test !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        },0,1l, TimeUnit.SECONDS);


    }

}
