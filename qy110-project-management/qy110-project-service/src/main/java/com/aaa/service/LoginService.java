package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.model.User;
import com.aaa.vo.TokenVo;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: Joy
 * @Date: 2020/7/15 15:12
 * @Description:
 */
@Service
public class LoginService extends BaseService<User> {
    public TokenVo doLogin(User user) {
        TokenVo tokenVo = new TokenVo();
        User user1 = new User();
        if (null != user) {
            user1.setUsername(user.getUsername());
            User user2 = super.selectOne(user1);
            if (null == user2) {
                tokenVo.setIfSuccess(false).setType(1);
                return tokenVo;
            } else {
                if (null != user.getPassword()) {
                    user1.setPassword(user.getPassword());
                } else {
                    return tokenVo.setIfSuccess(false).setType(5);
                }
                User user3 = super.selectOne(user1);
                if (null == user3) {
                    tokenVo.setIfSuccess(false).setType(2);
                    return tokenVo;
                } else {
                    /**
                     * !!!!!!mybatis是无法检测连接符的，他会把连接符进行转译(\\-)
                     * 需要把连接符替换掉
                     */
                    String token = UUID.randomUUID().toString().replaceAll("-", "");
                    user3.setToken(token);
                    Integer updateResult = super.update(user3);
                    if (updateResult > 0) {
                        tokenVo.setIfSuccess(true).setToken(token);
                    } else {
                        tokenVo.setIfSuccess(false).setType(4);
                        return tokenVo;
                    }
                }
            }
        } else {
            tokenVo.setIfSuccess(false).setType(4);
            return tokenVo;
        }
        return tokenVo;
    }
}
