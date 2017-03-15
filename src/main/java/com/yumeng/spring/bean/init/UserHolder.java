package com.yumeng.spring.bean.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yumeng on 2017/3/15.
 */
@Component
public class UserHolder {
    @Autowired
    private User aa;

    @Autowired
    private User bb;

    public String getName(){
        return aa.getUsername();
    }
    public boolean isTrue(){
        return aa == bb;
    }

}
