package com.yumeng.spring.lambda;

/**
 * Created by yumeng on 2017/3/12.
 */
public class User1 {

    private int age;

    public User1(int age, String name) {
        this.name=name;
        this.age=age;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
