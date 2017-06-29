package com.yumeng.spring.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by yumeng on 2017/4/25.
 */
public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2", "value2");
        map.putIfAbsent("key3", "value3");
        System.out.println(map);


       // computeIfAbsent 可以限制添加元素的条件,如下代码

        map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2", "value2");
        map.computeIfAbsent("key3", new Function<String,String>(){

            public String apply(String s) {
                System.out.println("s = [" + s + "]");
                if (s.equals("key3"))
                    return null;
                else
                    return s;
            }
        });
        System.out.println(map);
    }
}
