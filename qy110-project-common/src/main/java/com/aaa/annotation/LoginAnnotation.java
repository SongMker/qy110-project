package com.aaa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Joy
 * @Date: 2020/7/15 15:20
 * @Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAnnotation {
    /**
     * 要执行冲操作类型
     * @return
     */
    String operationType();

    /**
     * 具体执行的操作
     * @return
     */
    String operationName();
}
