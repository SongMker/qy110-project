package com.aaa.controller;

import com.aaa.annotation.LoginAnnotation;
import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.LoginService;
import com.aaa.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.status.LoginStatus.*;

/**
 * @Author: Joy
 * @Date: 2020/7/15 15:11
 * @Description:
 */
@RestController
public class LoginController extends CommonController<User> {
    @Autowired
    private LoginService loginService;

    @Override
    public BaseService<User> getBaseService() {
        return loginService;
    }

    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 登录操作
     * @Date 2020/7/15
     **/
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user) {
        System.out.println("用户是" + user);
        TokenVo tokenVo = loginService.doLogin(user);
        if (tokenVo.getIfSuccess()) {
            return super.loginSuccess("登录成功", tokenVo.getToken());
        } else if (tokenVo.getType() == 1) {
            return super.loginFailed(USER_NOT_EXIST.getMsg());
        } else if (tokenVo.getType() == 2) {
            return super.loginFailed(PASSWORD_WRONG.getMsg());
        } else if (tokenVo.getType() == 5) {
            return super.loginFailed("密码不能为空");
        } else {
            return super.loginFailed(SYSTEM_EXCEPTION.getMsg());
        }
    }
}
