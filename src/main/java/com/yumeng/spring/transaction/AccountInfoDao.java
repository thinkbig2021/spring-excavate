package com.yumeng.spring.transaction;

import com.zhibitech.framework.core.dao.annotation.MyBatisDao;

@MyBatisDao
public interface AccountInfoDao {
	void update(AccountInfo accountInfo);

}
