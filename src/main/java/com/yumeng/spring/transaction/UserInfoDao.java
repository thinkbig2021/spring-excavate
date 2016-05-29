package com.yumeng.spring.transaction;

import com.zhibitech.framework.core.dao.annotation.MyBatisDao;

@MyBatisDao
public interface UserInfoDao {
    void update(UserInfo userInfo);
}
