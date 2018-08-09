package com.yumeng.spring.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * Created by yumeng on 2017/7/12.
 */
public class Test {
    @org.junit.Test
    public void testThen() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            return "zero";
        }, executor);

        CompletableFuture<Integer> f2 = f1.thenApply(new Function<String, Integer>() {

            @Override
            public Integer apply(String t) {
                System.out.println(2);
                return Integer.valueOf(t.length());
            }
        });

        CompletableFuture<Double> f3 = f2.thenApply(r -> r * 2.0);
        System.out.println(f3.get());
    }
      @org.junit.Test
    public void testThenAccept(){
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "zero";
        }, executor);
        f1.thenAccept(e -> {
            System.out.println("get result:"+e);
        });
    }
}
