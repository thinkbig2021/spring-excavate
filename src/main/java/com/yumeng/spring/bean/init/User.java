package com.yumeng.spring.bean.init;

import org.springframework.stereotype.Component;

/**
 * Created by yumeng on 2017/3/15.
 */
@Component
public class User {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;


}
