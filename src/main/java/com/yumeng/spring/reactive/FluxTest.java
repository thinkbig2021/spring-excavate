package com.yumeng.spring.reactive;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FluxTest {

    @Test
    public void test1(){


        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        List<String> list1 = Arrays.asList("1","2","3");

        Flux.fromIterable(list1).reduce(list,(data,s) ->{

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            return data;

        });
    }

    @Test
    public void test2(){

        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);

        List<Integer> integerList = Flux.fromIterable(list).flatMap(i -> {
            return Mono.fromCallable(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(1000*i);
                    System.out.println("thread id === " + Thread.currentThread().getName());
                    return i * i;
                }
            }).publishOn(Schedulers.fromExecutor(Executors.newFixedThreadPool(3)));
        }).collectList().block();


        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));

        System.out.println(integerList);
    }
}
