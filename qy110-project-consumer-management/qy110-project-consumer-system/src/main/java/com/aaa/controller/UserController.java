package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joy
 * @Date: 2020/7/16 15:00
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @param []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select all users
     * @Date 2020/7/16
     **/
    @GetMapping("/allUser")
    ResultData allUser() {
        return iProjectService.allUser();
    }

    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add user
     * @Date 2020/7/16
     **/
    @PostMapping("/addUser")
    ResultData addUser(@RequestBody User user) {
        return iProjectService.addUser(user);
    }

    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete user by id
     * @Date 2020/7/16
     **/
    @PostMapping("/delUser")
    ResultData delUser(@RequestBody User user) {
        return iProjectService.delUser(user);
    }

    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update user
     * @Date 2020/7/16
     **/
    @PostMapping("/updateUser")
    ResultData updUser(@RequestBody User user) {
        return iProjectService.updUser(user);
    }
}
