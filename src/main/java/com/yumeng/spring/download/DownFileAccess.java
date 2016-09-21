package com.yumeng.spring.download;

import java.io.*;

import com.hp.hpl.sparta.Document.Index;

/**
 * 文件对象
 * 
 * @author wzztestin
 * 
 */
public class DownFileAccess implements Serializable {
	/** 
	 *  
	 */
	private static final long serialVersionUID = -2518013155676212866L;
	// 写入文件的流
	public RandomAccessFile oSavedFile;
	// 开始位置
	private long startP;
	// 结束位置
	private long endP;
	// 文件名
	private String name;

	// 是否是第一次下载；
	private boolean isFirst;

	public RandomAccessFile getoSavedFile() {
		return oSavedFile;
	}

	public void setoSavedFile(RandomAccessFile oSavedFile) {
		this.oSavedFile = oSavedFile;
	}

	public long getStartP() {
		return startP;
	}

	public void setStartP(long startP) {
		this.startP = startP;
	}

	public long getEndP() {
		return endP;
	}

	public void setEndP(long endP) {
		this.endP = endP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public DownFileAccess() throws IOException {
		this("", 0, 0, 0, true);
	}

	/**
	 * 写入文件初始化
	 * 
	 * @param sName
	 * @param nPos
	 * @throws IOException
	 */
	public DownFileAccess(String name, int blockIndex, long startP, long endP, boolean isFirst) throws IOException {
		File wfile = new File(name);
		System.out.println("old file " + name + " size is :" + wfile.length());
		oSavedFile = new RandomAccessFile(wfile, "rw");
		if (!isFirst) {
			oSavedFile.seek(wfile.length());
		}
		this.startP = startP;
		this.endP = endP;
		this.isFirst = isFirst;
	}

	/**
	 * 写文件
	 * 
	 * @param b
	 * @param nStart
	 * @param nLen
	 * @return
	 */
	public synchronized int write(byte[] b, int nStart, int nLen) {
		int n = -1;
		try {
			oSavedFile.write(b, nStart, nLen);
			n = nLen;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println(n+"-------------");
		return n;
	}
}