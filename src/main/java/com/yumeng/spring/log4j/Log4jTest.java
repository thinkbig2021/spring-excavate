package com.yumeng.spring.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yumeng on 2017/3/16.
 */
public class Log4jTest {
    private static Logger logger = LoggerFactory.getLogger(Log4jTest.class);
    public static void main(String[] args) {
        logger.info("============");
    }
}
