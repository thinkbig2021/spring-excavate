package com.yumeng.spring.download;

public class TestMethod {
	public TestMethod() {
		try {
			DownFileInfoBean bean = new DownFileInfoBean(
					"http://dldir1.qq.com/qqfile/qq/QQ8.6/18804/QQ8.6.exe",
					"D:\\temp", "QQ8.6.exe", 1);
			/*
			 * File file = new File("D:\\dan07.apk"); DownFileInfoBean bean =
			 * new DownFileInfoBean(null, "D:\\temp", "dan07.apk",
			 * 3,false,file);
			 */
			DownFileFetch fileFetch = new DownFileFetch(bean);
			fileFetch.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TestMethod();
	}
}