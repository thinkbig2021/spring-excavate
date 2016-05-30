package com.yumeng.spring.bean.bind;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;

public class Test {
	public static void main(String[] args) {
		List<User> userList = new ArrayList<User>();

		String columnHeads[] = { "age", "birth", "name", "lock" };
		String columnValue[][] = { { "28", "2016-05-28", "yumeng", "0" }, { "29", "2016-05-29", "yuchao", "0" },
				{ "30", "2016-05-30", "zhaocan", "1" } };
		for (int i = 0; i < columnValue.length; i++) {
			User user = new User();
			BeanWrapper bw = new BeanWrapperImpl(user);
			bw.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
			String values[] = columnValue[i];
			for (int j = 0; j < columnHeads.length; j++) {
				bw.setPropertyValue(columnHeads[j], values[j]);
			}
			userList.add(user);

		}

		if (userList.size() > 0) {
			for (User u : userList) {
				System.out.println("姓名：" + u.getName());
				System.out.println("年龄：" + u.getAge());
				System.out.println("生日：" + u.getBirth());
				System.out.println("是否锁定：" + u.getLock());
			}
		}
		try {
			Class test = Class.forName("com.yumeng.spring.bean.bind.Test");
			Method add = test.getDeclaredMethod("add", null);
			System.out.println(add.getReturnType().getTypeName());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public int add(){
		return 0;
	}
}
