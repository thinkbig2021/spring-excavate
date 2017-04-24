package com.yumeng.spring.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 测试方法 
 * @author zhangwei_david 
 * @version $Id: TestMain.java, v 0.1 2015年6月20日 下午4:33:35 zhangwei_david Exp $ 
 */  
public class TestMain {  
    public static void main(String[] args) throws Exception {  
        //获取MBeanServer  
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
  
        //构造一个ObjectName  
        ObjectName mxbeanName = new ObjectName("com.example:type=QueueSampler");
  
        // 创建一个队列  
        Queue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("Request-1");  
        queue.add("Request-2");  
        queue.add("Request-3");  
        // 构造一个mxbean  
        QueueSampler mxbean = new QueueSampler(queue);  
  
        // 注册mxbean  
        mbs.registerMBean(mxbean, mxbeanName);  
  
        //等待  
        System.out.println("Waiting for incoming requests...");  
        Thread.sleep(Long.MAX_VALUE);  
    }  
}  