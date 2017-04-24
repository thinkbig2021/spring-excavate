/** 
 * Alipay.com Inc. 
 * Copyright (c) 2004-2015 All Rights Reserved. 
 */
package com.yumeng.spring.jmx;
  
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.MemoryUsage;
import java.text.MessageFormat;
import java.util.Set;
  
/** 
 * 
 * @author zhangwei_david 
 * @version $Id: ClientTest.java, v 0.1 2015年6月20日 下午4:52:49 zhangwei_david Exp $ 
 */  
public class ClientTest {  
  
    private static final long   KB_SIZE     = 1024;  
  
    private static final String LOG_PATTERN = "{0}: 分配 {1} KB;  最大值 {2} KB; 已使用 {3} KB;  使用率 {4} %";  
  
    /** 
     * 
     * @param args 
     * @throws Exception 
     */  
    public static void main(String[] args) throws Exception {  
        JMXServiceURL serviceURL = new JMXServiceURL(  
                "service:jmx:rmi://localhost/jndi/rmi://localhost:9999/jmxrmi");  
        JMXConnector jmxc = JMXConnectorFactory.connect(serviceURL);  
        MBeanServerConnection msc = jmxc.getMBeanServerConnection();  
        // 获取所有的ObjectName  
        Set<ObjectName> objectNames = msc.queryNames(null, null);  
        for (ObjectName objectName : objectNames) {  
            System.out.println("ObjectName:" + objectName.getCanonicalName() + ".");  
        }  
  
        ObjectName name = new ObjectName("java.lang:type=OperatingSystem");  
        System.out.println(msc.getAttributes(name, new String[] { "CommittedVirtualMemorySize",  
                                                                  "FreePhysicalMemorySize", "FreeSwapSpaceSize" }));  
  
        printLog(msc, "java.lang:name=Metaspace,type=MemoryPool",  
            "java.lang:name=Survivor Space,type=MemoryPool",  
            "java.lang:name=Eden Space,type=MemoryPool",  
            "java.lang:name=Code Cache,type=MemoryPool",  
                "java.lang:name=Tenured Gen,type=MemoryPool");  
    }  
  
    private static void printLog(MBeanServerConnection msc, String... name) throws Exception {  
        for (String string : name) {  
            log(string, getUsageByName(msc, string));  
        }  
    }  
  
    private static MemoryUsage getUsageByName(MBeanServerConnection msc, String name)  
            throws Exception {  
        return MemoryUsage.from((CompositeDataSupport) msc.getAttribute(new ObjectName(name),  
            "Usage"));  
    }  
  
    private static void log(String key, MemoryUsage usage) {  
        System.out.println();  
        System.out.println(MessageFormat.format(LOG_PATTERN, key, usage.getCommitted() / KB_SIZE,  
            usage.getMax() / KB_SIZE, usage.getUsed() / KB_SIZE,  
            usage.getUsed() * 100 / usage.getCommitted()));  
    }  
}  