package com.yumeng.spring.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class CmdTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String cmd = "cmd /c cd /d C:\\Program Files\\Oracle\\VirtualBox"	+ "&&VBoxManage import android-0920.ova";
  
		final Process p = Runtime.getRuntime().exec("cmd /c cp D:\\vb\\a.txt e:\\");
//		OutputStream out = p.getOutputStream();
//        out.write("VBoxManage import android-0920.ova".getBytes());
		Thread thread = new Thread() {
			public void run() {

				InputStream stream = p.getInputStream();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new InputStreamReader(stream, "gbk"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String line = null;
				try {
					while ((line = reader.readLine()) != null) {
						System.out.println("111111111111");
						System.out.println(line);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread thread1 = new Thread() {
			public void run() {

				InputStream stream = p.getErrorStream();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new InputStreamReader(stream, "gbk"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String line = "";
				try {
					while ((line = reader.readLine()) != null) {
						System.out.println("22222222222");
						System.out.println(line);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread.start();
		thread1.start();
		p.waitFor();
		p.destroy();

	

	}
}
