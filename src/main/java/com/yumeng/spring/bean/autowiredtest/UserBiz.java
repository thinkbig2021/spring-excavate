package com.yumeng.spring.bean.autowiredtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yumeng on 2017/4/9.
 */
@Component
public class UserBiz {
//    @Autowired
//    private User user;
    @Resource
    private User user1;
    @Autowired
    private User user2;
    @Autowired
    private User user3;
    @Autowired
    List<UserService> userServiceList;

    @Autowired
    List<User> userList;
    @Autowired
    private Callback ss;
    @Autowired
    private MessageConsumer m;
    public void dosomething(){
        System.out.println(user1.getName());
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(userServiceList.size());
        System.out.println(userList.size());
    }
}
