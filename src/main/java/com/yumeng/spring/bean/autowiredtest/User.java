package com.yumeng.spring.bean.autowiredtest;

import org.springframework.stereotype.Component;

/**
 * Created by yumeng on 2017/4/9.
 */
@Component("333")
public class User {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
