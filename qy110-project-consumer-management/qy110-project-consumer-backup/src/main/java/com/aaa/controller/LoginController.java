package com.aaa.controller;

import com.aaa.annotation.LoginAnnotation;
import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joy
 * @Date: 2020/7/15 14:57
 * @Description:
 */
@RestController
public class LoginController extends BaseController {


    @Autowired
    private IProjectService iProjectService;
    /**
     * @param  [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:  登录操作
     * @Date 2020/7/15
     **/
    @PostMapping("/doLogin")
    @LoginAnnotation(operationType = "登录",operationName = "管理员登录")
    public ResultData doLogin(@RequestBody User user) {
        return iProjectService.doLogin(user);
    }

}
