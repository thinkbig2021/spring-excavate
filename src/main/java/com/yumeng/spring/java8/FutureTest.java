package com.yumeng.spring.java8;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FutureTest {

    @Test
    public void test() {


        CompletableFuture<String> future = CompletableFuture.supplyAsync(FutureTest::a);

    }

    @Test
    public void thenAccept() {
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s + " world"));
    }

    @Test
    public void thenApply() {
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApplyAsync(s -> s + " world").join();
        System.out.println(result);
    }

    @Test
    public void thenApply1() {
        long a = System.currentTimeMillis();
        String result = CompletableFuture.supplyAsync(FutureTest::a).thenApplyAsync(s -> s + " world").join();
        long b = System.currentTimeMillis();
        System.out.println((b - a) / 1000);
        System.out.println(result);
    }

    public static String a() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    public static String b() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "world";
    }

    @Test
    public void thenCombine() {
        long a = System.currentTimeMillis();
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
        long b = System.currentTimeMillis();
        System.out.println((b - a) / 1000);
    }

    @Test
    public void testThenRun() {
        long a = System.currentTimeMillis();
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1111111111");
            return "hello";
        }).thenRunAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("2222222222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long b = System.currentTimeMillis();
        System.out.println((b - a) / 1000);

    }

    @Test
    public void thenRun1() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenRun(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("2222222222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while (true) {
        }
    }

    @Test
    public void exceptionally() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    @Test
    public void funTest() {


        Function<Integer, Integer> fun1 = (x) -> {

            int y = x + 1;

            return y;

        };

        Function<Integer, Integer> fun2 = (x) -> {

            int y = x * 2;

            return y;

        };

        System.out.println(fun1.andThen(fun2).apply(1));

        List<Integer> list2 = Stream.of(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(6, 7, 8, 9, 10)).flatMap(t -> t.stream()).collect(Collectors.toList());

        System.out.println(list2.toString());
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> list1 = list.stream().map(t -> t.toString()).map(s -> Integer.parseInt(s)).collect(Collectors.toList());


    }

    @Test
    public void thenCombine2() {
        long a = System.currentTimeMillis();

        Supplier<String> supplier1 = () -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        };
        Supplier<String> supplier2 = () -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        };
        String result = CompletableFuture.supplyAsync(supplier1).thenCombineAsync(CompletableFuture.supplyAsync(supplier2), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
        long b = System.currentTimeMillis();
        System.out.println((b - a) / 1000);
    }

    @Test
    public void streamTest() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        long s = System.currentTimeMillis();
        List<Integer> list2 = list.parallelStream().map((t) -> {

            try {
                Thread.sleep(2000);
                System.out.println(222);
                return 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 2;
            }

        }).collect(Collectors.toList());
        System.out.println(1111);
        long b = System.currentTimeMillis();
        System.out.println(b - s);

    }


    @Test
    public void completaableTest() {

        Stopwatch stopwatch = Stopwatch.createStarted();

        CompletableFuture<String> t1 = CompletableFuture.supplyAsync(() -> {


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "method1";
        });


        CompletableFuture<String> t2 = CompletableFuture.supplyAsync(() -> {


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "method2";
        });

        CompletableFuture<String> t3 = CompletableFuture.supplyAsync(() -> {


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "method3";
        });
        System.out.println("11111111");
        CompletableFuture.allOf(t1, t2, t3).join();
        System.out.println("执行总耗时=======" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        System.out.println("22223222");


    }

    @Test
    public void test5() {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        long s = System.currentTimeMillis();

        List<CompletableFuture<Integer>> futures = integers.stream()
                .map(key -> {
                            return CompletableFuture.supplyAsync(() -> {

                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return key;
                            });
                        }
                )
                .collect(Collectors.toList());

        List<Integer> delKeys = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis()-s);

    }

    @Test
    public void test6() {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        long s = System.currentTimeMillis();

        integers.stream()
                .map(key -> {
                            return CompletableFuture.supplyAsync(() -> {

                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return key;
                            }).join();
                        }
                )
                .collect(Collectors.toList());

        System.out.println(System.currentTimeMillis()-s);

    }


}
