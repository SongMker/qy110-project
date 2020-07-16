package com.aaa.controller;


import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/16 14:39
 * @Description:
 */
@RestController
public class UserController extends CommonController<User> {
    @Autowired
    private UserService userService;


    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }

    /**
     * @param []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select all users
     * @Date 2020/7/16
     **/
    @GetMapping("/allUser")
    public ResultData allUser() {
        List<Map> maps = userService.allUser();
        if (maps.size() > 0) {
            return super.operationSuccess(maps);
        }
        return super.operationFailed();
    }

    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add user
     * @Date 2020/7/16
     **/
    @PostMapping("/addUser")
    public ResultData addUser(@RequestBody User user) {
        return super.addByT(user);
    }
    /**
     * @param  [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:  delete user by id
     * @Date 2020/7/16
     **/
    @PostMapping("/delUser")
    public ResultData delUser(@RequestBody User user) {
        return super.deleteByT(user);
    }
    /**
     * @param  [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:  update user
     * @Date 2020/7/16
     **/
    @PostMapping("/updateUser")
    public ResultData updUser(@RequestBody User user){
        return super.updateByT(user);
    }
    //TODO 暂时没写分页查询 按条件查询
}
