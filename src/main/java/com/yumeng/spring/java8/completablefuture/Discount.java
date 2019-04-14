package com.yumeng.spring.java8.completablefuture;

public enum Discount {
 
    NONE(0),SILVER(5),GOLD(10),PLATNUM(15),DIAMOND(20);
 
    private final int percent;
 
    Discount(int percent){
        this.percent = percent;
    }
 
    public int getPercent() {
        return percent;
    }
 
}
