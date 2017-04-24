package com.yumeng.spring.reflect;

import java.lang.reflect.Field;

public class TestPrivate2
{
    public static void main(String[] args) throws Exception
    {
        PrivateClass2 p = new PrivateClass2();
        Class<?> classType = p.getClass();

        Field field = classType.getDeclaredField("name");

        field.setAccessible(true); // 抑制Java对修饰符的检查
        String s = (String)field.get(p);

        System.out.println(s);
    }

}