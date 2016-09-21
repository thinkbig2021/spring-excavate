package com.yumeng.spring.download;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  姓名 工号
 * @version  [版本号, 2016年9月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class DownFileInfoBean {  
    private String sSiteURL; // 文件的下载地址  
    private String sFilePath; // 保存文件的路径  
    private String sFileName; // 保存文件的名字  
    private int nSplitter; // 文件分成几段，默认是5段  
      
    /** 
     * 默认初始化 
     */  
    public DownFileInfoBean() {  
        // default 5  
        this("", "", "", 5);  
    }  
      
    public DownFileInfoBean(String sURL, String sPath, String sName, int nSpiltter) {  
        sSiteURL = sURL;  
        sFilePath = sPath;  
        sFileName = sName;  
        this.nSplitter = nSpiltter;  
    }  
  
    public String getSSiteURL() {  
        return sSiteURL;  
    }  
  
    public void setSSiteURL(String value) {  
        sSiteURL = value;  
    }  
  
    public String getSFilePath() {  
        return sFilePath;  
    }  
  
    public void setSFilePath(String value) {  
        sFilePath = value;  
    }  
  
    public String getSFileName() {  
        return sFileName;  
    }  
  
    public void setSFileName(String value) {  
        sFileName = value;  
    }  
  
    public int getNSplitter() {  
        return nSplitter;  
    }  
  
    public void setNSplitter(int nCount) {  
        nSplitter = nCount;  
    }  
}  
 