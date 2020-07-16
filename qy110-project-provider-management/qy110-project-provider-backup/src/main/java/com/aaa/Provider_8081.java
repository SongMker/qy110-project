package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: Joy
 * @Date: 2020/7/15 16:08
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.aaa.mapper")
public class Provider_8081 {
    public static void main(String[] args) {
        SpringApplication.run(Provider_8081.class, args);
    }
}
