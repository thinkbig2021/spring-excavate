package com.yumeng.spring.base;

/**
 * Created by yumeng on 2017/5/10.
 */
public class Test
{
    public static void main(String[] args) {
        byte e = 0b00000001;
        System.out.println(e);
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(1));
        System.out.println(Long.toBinaryString(-100));
        System.out.println(Integer.toBinaryString(9));
        byte[] a = new byte[10];
        a[0]= -127;
        System.out.println(a[0]);
        byte c = (byte)(a[0]&0xff);
        System.out.println(c);
        int ii = 1 >>> 32;
        System.out.println(ii);
    }
}
