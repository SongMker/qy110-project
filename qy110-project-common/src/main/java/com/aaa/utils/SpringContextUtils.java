package com.aaa.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Joy
 * @Date: 2020/7/9 15:36
 * @Description:
 */
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT = null;
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    private SpringContextUtils() {
    }

    /**
     * 自己写的spring上下文替代了spring本身的 但是当spring加载时仍然会去覆盖掉自己定义的
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        Lock lock = READ_WRITE_LOCK.readLock();
        try {
            if (null != APPLICATION_CONTEXT) {
                return APPLICATION_CONTEXT;
            } else {
                return null;
            }

        } finally {
            lock.unlock();
        }
    }

}
