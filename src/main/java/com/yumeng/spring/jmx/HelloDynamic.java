package com.yumeng.spring.jmx;

import javax.management.*;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;

/**
 * 动态MBean 示例 
 *  
 * @author zhangwei_david 
 * @version $Id: HelloDynamic.java, v 0.1 2015年6月21日 下午10:53:07 zhangwei_david Exp $ 
 */  
public class HelloDynamic implements DynamicMBean {
  
    // 管理控件（MBean）属性  
    private String                  name;  
  
    // 动态创建MBean需要的变量  
    private String                  className   = this.getClass().getName();  
    // 描述  
    private String                  description = "Simple implementation of a dynamic MBean.";  
    //管理资源  
    private MBeanAttributeInfo[]    attributes;
    // 构造方法  
    private MBeanConstructorInfo[]  constructors;
    // 操作  
    private MBeanOperationInfo[]    operations;
  
    private MBeanInfo               mBeanInfo;  
    // 通知  
    private MBeanNotificationInfo[] notifications;
  
    /** 
     * 构造方法 
     */  
    public HelloDynamic() {  
        init();  
        buildDynamicMBean();  
    }  
  
    private void init() {  
        className = this.getClass().getName();  
        description = "Simple implementation of a dynamic MBean.";  
        attributes = new MBeanAttributeInfo[1];  
        constructors = new MBeanConstructorInfo[1];  
        operations = new MBeanOperationInfo[1];  
        notifications = new MBeanNotificationInfo[0];  
    }  
  
    private void buildDynamicMBean() {  
        // 构造方法  
        Constructor<?>[] ctors = this.getClass().getConstructors();
        constructors[0] = new MBeanConstructorInfo(  
            "HelloDynamic(): Constructs a HelloDynamic object", ctors[0]);  
        // 属性  
        attributes[0] = new MBeanAttributeInfo("name", "java.lang.String", "Name: name string",  
            true, true, false);  
        // 方法  
        MBeanParameterInfo[] params = null;  
        operations[0] = new MBeanOperationInfo("print", "print(): print the name", params, "void",  
            MBeanOperationInfo.INFO);  
        // MBeanInfo  
        mBeanInfo = new MBeanInfo(className, description, attributes, constructors, operations,  
            notifications);  
    }  
  
    /** 
     * 
     * @see javax.management.DynamicMBean#getAttribute(java.lang.String) 
     */  
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException,  
                                                ReflectionException {  
        if (attribute == null) {
            return null;  
        }  
        if ("name".equals(attribute)) {  
            return name;  
        }  
        return null;  
    }  
  
    public AttributeList getAttributes(String[] attributes) {  
        if (attributes == null) {
            return null;  
        }  
        AttributeList reslist = new AttributeList();  
  
        for (String attr : attributes) {  
            try {  
                Object value = getAttribute(attr);  
                reslist.add(new Attribute(attr, value));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
  
        return reslist;  
    }  
  
    /** 
     * 
     * @see javax.management.DynamicMBean#getMBeanInfo() 
     */  
    public MBeanInfo getMBeanInfo() {  
        return mBeanInfo;  
    }  
  
    /** 
     * 
     * @see javax.management.DynamicMBean#invoke(java.lang.String, java.lang.Object[], java.lang.String[]) 
     */  
    public Object invoke(String actionName, Object[] params, String[] signature)  
                                                                                throws MBeanException,  
                                                                                ReflectionException {  
        if (actionName.equals("print")) {
            print();  
        } else if ("dynamicPrint".equals(actionName)) {  
            dynamicPrint();  
        }  
        return null;  
    }  
  
    /** 
     * 
     * @see javax.management.DynamicMBean#setAttribute(javax.management.Attribute) 
     */  
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException,  
                                                 InvalidAttributeValueException, MBeanException,  
                                                 ReflectionException {  
        if (attribute == null) {  
            return;  
        }  
  
        String attrname = attribute.getName();  
        Object attrvalue = attribute.getValue();  
  
        if ("name".equals(attrname)) {  
            if (attrvalue == null) {  
                name = null;  
            } else {  
                try {  
                    if (Class.forName("java.lang.String").isAssignableFrom(attrvalue.getClass())) {  
                        name = (String) attrvalue;  
                    }  
                } catch (ClassNotFoundException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
  
    }  
  
    /** 
     * 
     * @see javax.management.DynamicMBean#setAttributes(javax.management.AttributeList) 
     */  
    public AttributeList setAttributes(AttributeList attributes) {  
        if (attributes == null) {  
            return null;  
        }  
        AttributeList reslist = new AttributeList();  
        for (Object obj : attributes) {  
            Attribute attr = (Attribute) obj;  
            try {  
                setAttribute(attr);  
                String attrname = attr.getName();  
                Object attrvalue = attr.getValue();  
                reslist.add(new Attribute(attrname, attrvalue));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return reslist;  
    }  
  
    private void print() {  
        System.out.println(MessageFormat.format("Hello {0}, This is helloDynamic", name));  
        // add method dynamic at runtime  
        operations = new MBeanOperationInfo[2];  
        buildDynamicMBean();  
        MBeanParameterInfo[] parameters = null;  
        operations[1] = new MBeanOperationInfo("dynamicPrint",  
            "dynamicPrint: Runtime generated by print method", parameters, "void",  
            MBeanOperationInfo.INFO);  
    }  
  
    private void dynamicPrint() {  
        System.out.println("This is a runtime generated method!");  
    }  
  
}  