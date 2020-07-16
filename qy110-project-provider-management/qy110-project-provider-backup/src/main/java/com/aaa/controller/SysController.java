package com.aaa.controller;


import com.aaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/16 8:40
 * @Description:
 */
@RestController
public class SysController  {
    @Autowired
    private SysService sysService;

    @GetMapping("/allMenu")
    public List<Map> selectMenu( @RequestParam(value = "username") String username) {
        return sysService.selectMenu(username);
    }
}
