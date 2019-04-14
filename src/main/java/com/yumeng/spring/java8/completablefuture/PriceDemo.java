package com.yumeng.spring.java8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class PriceDemo {
    private List<Shop> shops = Arrays.asList(new Shop("shop1"),
            new Shop("shop2"),
            new Shop("shop3"),
            new Shop("shop4"),
            new Shop("shop5"),
            new Shop("shop6"),
            new Shop("shop7"),
            new Shop("shop8")
    );

    public List<String> findPrices(String product) {
        /*
        * 方法一：加并行流.parallel()
        * */
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f ", shop.getName(), shop.getPice(product)))
                .collect(Collectors.toList());
    }
    public List<String> findPrices1(String product){
        List<CompletableFuture<String>> priceFuture = shops.stream().map(shop -> CompletableFuture
                .supplyAsync(() -> String.format("%s price is %.2f ", shop.getName(), shop.getPice(product))))
                .collect(Collectors.toList());
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

//    public List<String> findPrices2(String product) {
//        Executor executor = Executors.newCachedThreadPool();
//        // Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100));
//        List<CompletableFuture<String>> priceFuture = shops.stream()
//                .map(shop -> CompletableFuture
//                        .supplyAsync(() -> shop.getPice(product), executor)
//                        .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeDemo.getRate("USD", "CNY"), executor),
//                                (quote, rate) -> new Quote(quote.getShop(), quote.getPrice() * rate, quote.getDiscount())))//这返回的是异步处理
//                //.map(future->future.thenApply(Quote::parse))//thenApp是前一个对象完成了之后调下个对象的方法（parse）
//                .map(future -> future.thenCompose(quote ->//thenCompose是当前对象准备扔到一个异步操作里面
//                        CompletableFuture.supplyAsync(() ->   DiscountDemo.applyDiscount(quote), executor)))
//                .collect(Collectors.toList());
//        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
//    }


    public static void main(String[] args) {
        PriceDemo priceDemo = new PriceDemo();
        Long start = System.currentTimeMillis();
        System.out.println(priceDemo.findPrices1("苹果x"));
        System.out.println("服务耗时：" + (System.currentTimeMillis() - start));
    }
}