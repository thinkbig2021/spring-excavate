package com.yumeng.spring.extendstest;

/**
 * Created by yumeng on 2017/4/18.
 */
public class UserSub extends User{
    public void b(){
        System.out.println("usersub b");
    }

    public static void main(String[] args) {
        UserSub u = new UserSub();
        u.a();
    }
}
