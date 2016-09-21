package com.yumeng.spring;
public class DownLoadTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        String filepath = "http://url.tudown.com/down/%E9%AD%94%E5%85%BD%E4%BA%89%E9%9C%B83%EF%BC%9A%E5%86%B0%E5%B0%81%E7%8E%8B%E5%BA%A71.20.0.0@83_950.exe";
        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,2);    
        load.downloadPart();    
    }
}