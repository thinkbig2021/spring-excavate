package com.yumeng.spring.test;

/**
 * Created by yumeng on 2017/5/26.
 */
public class Test {
    public static void main(String[] args) {
        String s = a("1");
        System.out.println(s);

    }

    private static String a(String s) {
        if(s=="1"){
            return null;
        }
        return "2";
    }


}
