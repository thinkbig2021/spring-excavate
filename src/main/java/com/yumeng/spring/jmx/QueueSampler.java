package com.yumeng.spring.jmx;

import java.util.Date;
import java.util.Queue;

/**
 *MXBean的实现类 
 * @author zhangwei_david 
 * @version $Id: QueueSampler.java, v 0.1 2015年6月20日 下午4:32:19 zhangwei_david Exp $ 
 */  
public class QueueSampler implements QueueSamplerMXBean {
  
    private Queue<String> queue;  
  
    public QueueSampler(Queue<String> queue) {
        this.queue = queue;  
    }  
  
    public QueueSample getQueueSample() {  
        synchronized (queue) {  
            return new QueueSample(new Date(), queue.size(), queue.peek());
        }  
    }  
  
    public void clearQueue() {  
        synchronized (queue) {  
            queue.clear();  
        }  
    }

    @Override
    public void clearQueue1() {

    }

    @Override
    public void setName(String name) {
        System.out.println("name = [" + name + "]");
    }

}  