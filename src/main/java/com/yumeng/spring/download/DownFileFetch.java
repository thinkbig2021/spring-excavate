package com.yumeng.spring.download;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class DownFileFetch extends Thread {
	private DownFileInfoBean siteInfoBean = null; // 文件信息 Bean
	private DownFileSplitterFetch[] fileSplitterFetch; // 子线程对象
	private long nFileLength; // 文件长度
	private boolean bFirst = true; // 是否第一次取文件
	private boolean bStop = false; // 停止标志
	private File tmpFile; // 文件下载的临时信息
	private DataOutputStream output; // 输出到文件的输出流  
	// 分割线程数量
	private int splitter = 0;
	private long splitSize;
	private CountDownLatch countDownLatch;
	private long nStartPos[];
	private long nEndPos[];

	/**
	 * 下载上传文件抓取初始化
	 * 
	 * @param bean
	 * @throws IOException
	 */
	public DownFileFetch(DownFileInfoBean bean) throws IOException {
		this.siteInfoBean = bean;
		/**
		 * File.separator windows是\,unix是/
		 */
		this.tmpFile = new File(bean.getSFilePath() + File.separator + bean.getSFileName() + ".info");
		if (tmpFile.exists()) {
			this.bFirst = false;
		}
		this.splitter = bean.getNSplitter();
		this.nStartPos = new long[bean.getNSplitter()];
		this.nEndPos = new long[bean.getNSplitter()];
		this.countDownLatch = new CountDownLatch(bean.getNSplitter());
	}

	public void run() {
		// 获得文件长度
		// 分割文件
		// 实例 FileSplitterFetch
		// 启动 FileSplitterFetch 线程
		// 等待子线程返回
		try {
			nFileLength = getFileSize();
			splitSize = nFileLength/splitter;
			if (bFirst) {
				System.out.println("file size :" + nFileLength);
				if (nFileLength == -1) {
					DownFileUtility.log("File Length is not known!");
				} else if (nFileLength == -2) {
					DownFileUtility.log("File is not access!");
				} else {
					// 每段数据大小,保留整数位，最后一个分段进行计算；
					for (int i = 0; i < nStartPos.length; i++) {
						nStartPos[i] = (long) (i * splitSize);
						if (i == nStartPos.length - 1) {
							nEndPos[i] = nFileLength;
						} else {
							nEndPos[i] = splitSize * (i + 1);
						}

					}
				}
			} else {
				readHasDownloadFilePosition();
			}
			// 启动子线程
			fileSplitterFetch = new DownFileSplitterFetch[nStartPos.length];
			for (int i = 0; i < nStartPos.length; i++) {
				String fileName = siteInfoBean.getSFilePath() + File.separator + siteInfoBean.getSFileName() + "_" + i;
				DownFileAccess fileAccess = new DownFileAccess(fileName, i, nStartPos[i], nEndPos[i], bFirst);
				fileSplitterFetch[i] = new DownFileSplitterFetch(siteInfoBean.getSSiteURL(), i, countDownLatch,
						fileAccess);
				DownFileUtility.log("Thread " + i + " , nStartPos = " + nStartPos[i] + ", nEndPos = " + nEndPos[i]);
				fileSplitterFetch[i].start();
			}
			write_nPos();
			countDownLatch.await();
			hebinfile(siteInfoBean.getSFilePath() + File.separator + siteInfoBean.getSFileName(), splitter);
			DownFileUtility.log("文件下载结束！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得文件长度
	 * 
	 * @return
	 */
	public long getFileSize() {
		int nFileLength = -1;

		try {
			URL url = new URL(siteInfoBean.getSSiteURL());
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestProperty("User-Agent", "NetFox");
			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400) {
				processErrorCode(responseCode);
				// represent access is error
				return -2;
			}
			String sHeader;
			for (int i = 1;; i++) {
				sHeader = httpConnection.getHeaderFieldKey(i);
				if (sHeader != null) {
					if (sHeader.equals("Content-Length")) {
						nFileLength = Integer.parseInt(httpConnection.getHeaderField(sHeader));
						break;
					}
				} else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DownFileUtility.log(nFileLength);

		return nFileLength;
	}
	 private void write_nPos() {  
	        try {  
	            output = new DataOutputStream(new FileOutputStream(tmpFile));  
	            output.writeInt(splitter);  
	            output.writeLong(splitSize);
	            output.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	private void readHasDownloadFilePosition() {
		for (int i = 0; i < this.splitter; i++) {
			String fileName = siteInfoBean.getSFilePath() + File.separator + siteInfoBean.getSFileName() + "_" + i;
			File tempSubFile = new File(fileName);
			if (tempSubFile.exists()) {
				this.nStartPos[i] = this.splitSize * i + tempSubFile.length();
				if (i == this.splitter - 1) {
					nEndPos[i] = nFileLength;
				} else {
					nEndPos[i] = splitSize * (i + 1);
				}
			}
		}
	}

	/**
	 * 输出错误信息
	 * 
	 * @param nErrorCode
	 */
	private void processErrorCode(int nErrorCode) {
		DownFileUtility.log("Error Code : " + nErrorCode);
	}

	/**
	 * 停止文件下载
	 */
	public void siteStop() {
		bStop = true;
		for (int i = 0; i < nStartPos.length; i++)
			fileSplitterFetch[i].splitterStop();
	}

	/**
	 * 合并文件
	 * 
	 * @param sName
	 * @param splitternum
	 */
	private void hebinfile(String sName, int splitternum) {
		try {
			File file = new File(sName);
			if (file.exists()) {
				file.delete();
			}
			RandomAccessFile saveinput = new RandomAccessFile(sName, "rw");
			for (int i = 0; i < splitternum; i++) {
				try {
					RandomAccessFile input = new RandomAccessFile(new File(sName + "_" + i), "r");
					byte[] b = new byte[1024];
					int nRead;
					while ((nRead = input.read(b, 0, 1024)) > 0) {
						write(saveinput, b, 0, nRead);
					}
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			DownFileUtility.log("file size is " + saveinput.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写文件
	 * 
	 * @param b
	 * @param nStart
	 * @param nLen
	 * @return
	 */
	private int write(RandomAccessFile oSavedFile, byte[] b, int nStart, int nLen) {
		int n = -1;
		try {
			oSavedFile.seek(oSavedFile.length());
			oSavedFile.write(b, nStart, nLen);
			n = nLen;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}
}