package com.yumeng.spring.jmx;

import java.beans.ConstructorProperties;
import java.util.Date;

/**
 *MXBean参数对象 
 * @author zhangwei_david 
 * @version $Id: QueueSample.java, v 0.1 2015年6月20日 下午4:30:30 zhangwei_david Exp $ 
 */  
public class QueueSample {  
  
    private final Date date;
    private final int    size;  
    private final String head;  
  
    @ConstructorProperties({ "date", "size", "head" })
    public QueueSample(Date date, int size, String head) {  
        this.date = date;  
        this.size = size;  
        this.head = head;  
    }  
  
    public Date getDate() {  
        return date;  
    }  
  
    public int getSize() {  
        return size;  
    }  
  
    public String getHead() {  
        return head;  
    }  
}  