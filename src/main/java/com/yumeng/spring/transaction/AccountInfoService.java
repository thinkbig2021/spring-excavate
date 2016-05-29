package com.yumeng.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountInfoService")
public class AccountInfoService {
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void update(AccountInfo accountInfo){
		accountInfoDao.update(accountInfo);
		throw new RuntimeException("回滚了");
	}

}
