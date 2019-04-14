package com.yumeng.spring.java8.completablefuture;

public class ExchangeDemo {
 
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}
    public static double getRate(String source,String target){
        delay();
        return 10;
    }

//    public List<String> findPrices(String product) {
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

}
