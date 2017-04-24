package com.yumeng.spring.jmx;

/**
 *MXBean接口，定义两个操作 
 * 
 * @author zhangwei_david 
 * @version $Id: QueueSamplerMXBean.java, v 0.1 2015年6月20日 下午4:31:34 zhangwei_david Exp $ 
 */  
public interface QueueSamplerMXBean {  
  
    public QueueSample getQueueSample();  
  
    public void clearQueue();
    public void clearQueue1();

    public void setName(String name);
}  
