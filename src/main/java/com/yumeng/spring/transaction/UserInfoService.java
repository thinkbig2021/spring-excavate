package com.yumeng.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userInfoService")
public class UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private AccountInfoService accountInfoService;
	@Transactional(propagation= Propagation.REQUIRED)
	public void update(UserInfo userInfo,AccountInfo accountInfo){
		userInfoDao.update(userInfo);
		System.out.println("开启嵌套事务");
		try {
			accountInfoService.update(accountInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		//throw new RuntimeException();
	}

}
