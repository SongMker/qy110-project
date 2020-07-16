package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.UserMapper;
import com.aaa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/16 14:43
 * @Description:
 */
@Service
public class UserService extends BaseService<User> {
    @Autowired
    private UserMapper userMapper;

   public List<Map> allUser() {
        return userMapper.allUser();
    }

}
