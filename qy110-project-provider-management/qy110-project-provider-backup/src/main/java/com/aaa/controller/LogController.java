package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.model.LoginLog;
import com.aaa.service.LoginLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joy
 * @Date: 2020/7/15 15:32
 * @Description:
 */
@RestController
public class LogController extends CommonController<LoginLog> {
    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog) {
        return getBaseService().add(loginLog);
    }


    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }
}
