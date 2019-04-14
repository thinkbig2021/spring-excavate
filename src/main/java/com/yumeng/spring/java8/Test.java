package com.yumeng.spring.java8;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public void testThenAccept() {
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
            System.out.println("get result:" + e);
        });
    }
    @org.junit.Test
    public void bizfuntest(){


    }

    @org.junit.Test
    public void test1(){

        List<Integer> list = Arrays.asList(1,2,3,4);
        Stopwatch stopwatch = Stopwatch.createStarted();
        list.parallelStream().map((i)->{

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i*3;
        }).collect(Collectors.toList());

        System.out.println("=========耗时"+stopwatch.elapsed(TimeUnit.MILLISECONDS));

    }
}
