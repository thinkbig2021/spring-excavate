package com.yumeng.spring.reactive;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

public class MyTest {


    @Test
    public void test2() {
        Stopwatch stop = Stopwatch.createStarted();

        Flux.just(1, 2, 3).flatMap(t -> {


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t);
            return Flux.just(t);
        }).collect(toList()).block();
        System.out.println(stop.elapsed(TimeUnit.SECONDS));

    }

    @Test
    public void testConcurrencyAndPrefetch() {
        int concurrency = 3;
        int prefetch = 6;
        Flux.range(1, 100)
                .log()
                .flatMap(i -> Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).log(),
                        concurrency, prefetch)
                .subscribe();
    }

    @Test
    public void test3() {

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

            List<Integer> integerList = Objects.requireNonNull(Flux.fromIterable(list).flatMap(i -> {
            return Mono.fromCallable(new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    Thread.sleep(1000);
                    System.out.println("thread id === " + Thread.currentThread().getName());
                    return Lists.newArrayList(i * i, (i + 1) * i);
                }
            }).publishOn(Schedulers.fromExecutor(Executors.newFixedThreadPool(10)));
        }).collectList().block()).stream().flatMap(Collection::stream).collect(toList());


        System.out.println(integerList);
    }

    @Test
    public void test4() {

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> integerList = Flux.fromIterable(list).flatMap(i -> {
            return Mono.fromCallable(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(1000);
                    System.out.println("thread id === " + Thread.currentThread().getName());
                    return i * i;
                }
            }).publishOn(Schedulers.fromExecutor(Executors.newFixedThreadPool(10)));
        }).collectList().block(Duration.ofMillis(1000));


        System.out.println(integerList);
    }

    @Test
    public void test5() {

        List<Integer> list = Arrays.asList(1,2,3,4,5);


        Stopwatch stopwatch = Stopwatch.createStarted();
        Flux.fromIterable(list).flatMap(i -> {
            return Mono.fromRunnable(
                    () -> {
                        //这里做其他事情
                        try {
                            Thread.sleep(i*2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i);

                    }).publishOn(Schedulers.fromExecutor(Executors.newFixedThreadPool(3)));
        }).blockLast();

        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));


    }
}
