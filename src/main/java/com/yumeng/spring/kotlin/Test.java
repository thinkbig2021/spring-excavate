package com.yumeng.spring.kotlin;

import java.util.Date;

public class Test {
    public static void hello(){
        Date d = new Date();
        d.getTime();
        System.out.println("hello");
       FirstKt.a();
    }

    public static void main(String[] args) {
        hello();
    }
}
