package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: Joy
 * @Date: 2020/7/16 9:56
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.aaa.mapper")
public class Provider_8082_System {
    public static void main(String[] args) {
        SpringApplication.run(Provider_8082_System.class, args);
    }
}
