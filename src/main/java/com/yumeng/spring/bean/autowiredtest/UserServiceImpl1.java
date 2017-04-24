package com.yumeng.spring.bean.autowiredtest;

import org.springframework.stereotype.Component;

/**
 * Created by yumeng on 2017/4/9.
 */
@Component
public class UserServiceImpl1 implements  UserService{
    @Override
    public void findById(String id) {
        System.out.println("UserServiceImpl1");
    }
}
