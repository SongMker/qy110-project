package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.SysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/16 8:38
 * @Description:
 */
@Service
public class SysService  {
    @Autowired
    private SysMapper sysMapper;

    public List<Map> selectMenu(String username) {
        if (username != null) {
            return sysMapper.selectMenu(username);
        }
        return null;
    }
}
