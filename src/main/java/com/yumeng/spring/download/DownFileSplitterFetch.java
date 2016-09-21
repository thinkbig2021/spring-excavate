package com.yumeng.spring.download;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * 下载上传子线程
 * 
 * @author wzztestin
 * 
 */
public class DownFileSplitterFetch extends Thread {
	private String url; // 下载文件的地址
	private int threadId; // 线程的 ID
	private boolean bDownOver = false; // 是否下载完成
	private boolean bStop = false; // 停止下载
	private DownFileAccess fileAccess = null; // 文件对象
	private CountDownLatch countDownLatch;
	
	private long startLoopIndex ;

	public DownFileSplitterFetch(String url, int threadId, CountDownLatch countDownLatch, DownFileAccess fileAccess) {
		this.url = url;
		this.threadId = threadId;
		this.fileAccess = fileAccess;
		this.countDownLatch = countDownLatch;
	}

	/**
	 * 线程执行
	 */
	public void run() {
		this.urldownload();

	}

	/**
	 * 打印回应的头信息
	 * 
	 * @param con
	 */
	public void logResponseHead(HttpURLConnection con) {
		for (int i = 1;; i++) {
			String header = con.getHeaderFieldKey(i);
			if (header != null) {
				DownFileUtility.log(header + " : " + con.getHeaderField(header));
			} else {
				break;
			}
		}
	}

	/**
	 * 地址下载
	 */
	private void urldownload() {

		DownFileUtility
				.log("Thread " + threadId + " url down filesize is " + (fileAccess.getEndP() - fileAccess.getStartP()));
		DownFileUtility.log("Thread " + threadId + " url start >> " + fileAccess.getStartP() + "------end >> "
				+ fileAccess.getEndP());
		while (startLoopIndex < fileAccess.getEndP() && !bStop) {
			try {
				URL _url = new URL(url);
				HttpURLConnection httpConnection = (HttpURLConnection) _url.openConnection();
				httpConnection.setRequestProperty("User-Agent", "NetFox");
				String sProperty = "bytes=" + fileAccess.getStartP() + "-" + fileAccess.getEndP();
				httpConnection.setRequestProperty("RANGE", sProperty);
				DownFileUtility.log("thread" + threadId + "  " + sProperty);
				InputStream input = httpConnection.getInputStream();
				byte[] b = new byte[1024];
				int nRead;
				startLoopIndex = fileAccess.getStartP();
				while ((nRead = input.read(b, 0, 1024)) > 0 &&  startLoopIndex < fileAccess.getEndP() && !bStop) {
					if ((startLoopIndex + nRead) > fileAccess.getEndP()) {
						nRead = (int) (fileAccess.getEndP() - startLoopIndex);
					}
					startLoopIndex += fileAccess.write(b, 0, nRead);
					System.err.println(nRead);
					System.out.println(startLoopIndex);
				}
				DownFileUtility.log("Thread " + threadId + " nStartPos : " + fileAccess.getStartP());
				fileAccess.oSavedFile.close();
				DownFileUtility.log("Thread " + threadId + " is over!");
				input.close();
				bDownOver = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!bDownOver){  
	            if(startLoopIndex >= fileAccess.getStartP()){  
	                bDownOver = true;  
	            }  
	        }  
		}
		countDownLatch.countDown();

	}

	/**
	 * 停止
	 */
	public void splitterStop() {
		bStop = true;
	}
}