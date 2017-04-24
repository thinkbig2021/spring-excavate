package com.yumeng.spring.jmx;

/**
 *  
 *  
 * @author zhangwei_david 
 * @version $Id: HelloDynamicAgent.java, v 0.1 2015年6月21日 下午10:53:51 zhangwei_david Exp $ 
 */  
public class HelloDynamicAgent {  
//    private static String DOMAIN = "MyDynamicMBean";
//
//    /**
//     * @param args
//     * @throws NullPointerException
//     * @throws MalformedObjectNameException
//     * @throws NotCompliantMBeanException
//     * @throws MBeanRegistrationException
//     * @throws InstanceAlreadyExistsException
//     */
//    public static void main(String[] args) throws MalformedObjectNameException,
//                                          NullPointerException, InstanceAlreadyExistsException,
//            MBeanRegistrationException, NotCompliantMBeanException {
//        //创建一个MBean服务对象，DOMAIN类似于java里面的公共package部分
//        MBeanServer server = MBeanServerFactory.createMBeanServer(DOMAIN);
//        //创建DynamicMBean对象
//        HelloDynamic hello = new HelloDynamic();
//        //创建一个web适配器服务器，表示我们MBean服务通过web形式来提供给用户管理
//        HtmlAdaptorServer htmlserver = new HtmlAdaptorServer();
//        htmlserver.setPort(9999);
//        //ObjctName对象类似于完整的package
//        ObjectName helloname = new ObjectName(DOMAIN + ":name=HelloDynamic");
//        ObjectName htmlname = new ObjectName(DOMAIN + ":name=HtmlAdaptor");
//        server.registerMBean(hello, helloname);
//        server.registerMBean(htmlserver, htmlname);
//
//        htmlserver.start();
//    }
  
}  