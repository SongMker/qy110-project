package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Joy
 * @Date: 2020/7/15 14:56
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.aaa"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.aaa"})
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
