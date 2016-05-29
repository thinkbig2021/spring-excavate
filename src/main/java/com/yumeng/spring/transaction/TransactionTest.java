package com.yumeng.spring.transaction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring/spring-context.xml" });
		UserInfoService userInfoService = (UserInfoService)context.getBean("userInfoService");
		UserInfo user = new UserInfo();
		user.setId("1");
		user.setUserName("yumengggg");
		user.setPassWord("1");
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setId("3");
		accountInfo.setBalance("7");
		accountInfo.setPoint("bbb");
		userInfoService.update(user, accountInfo);

	}

}
