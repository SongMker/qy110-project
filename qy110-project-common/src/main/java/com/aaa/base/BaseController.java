package com.aaa.base;

import static com.aaa.status.LoginStatus.*;
import static com.aaa.status.OperationStatus.*;

/**
 * @Author: Joy
 * @Date: 2020/7/8 15:04
 * @Description: 统一controller 所有的controller都要继承这个类，进行统一返回
 */

public class BaseController {
    /**
     * @param []
     * @return com.joy.base.ResultData
     * @author Joy
     * @description 登录成功返回使用系统消息
     * @Date 2020/7/9
     **/
    protected ResultData loginSuccess() {
        ResultData<Object> resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }

    /**
     * @param [msg]
     * @return com.joy.base.ResultData
     * @author Joy
     * @description 自定义返回消息
     * @Date 2020/7/9
     **/
    protected ResultData loginSuccess(String msg) {
        ResultData<Object> resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * @param [msg, data]
     * @return com.joy.base.ResultData
     * @author Joy
     * @description 返回数据信息，使用系统消息
     * @Date 2020/7/9
     **/
    protected ResultData loginSuccess(String msg, Object data) {
        ResultData<Object> resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * @param []
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 登录失败，使用系统消息
     * @Date 2020/7/9
     **/
    protected ResultData loginFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }

    /**
     * @param [detail]
     * @return com.joy.base.ResultData
     * @author Joy
     * @description:登录失败，使用系统消息，详细解释说明
     * @Date 2020/7/9
     **/
    protected ResultData loginFailed(String detail) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }

    /**
     * @param []
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 操作成功返回系统消息
     * @Date 2020/7/9
     **/
    protected ResultData operationSuccess() {
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        return resultData;
    }


    /**
     * @param [msg, data]
     * @return com.joy.base.ResultData
     * @author Joy
     * @description:操作成功返回数据和自定义信息
     * @Date 2020/7/9
     **/
    protected ResultData operationSuccess(Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * @param []
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 操作失败返回系统消息
     * @Date 2020/7/9
     **/
    protected ResultData operationFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        return resultData;
    }

    /**
     * @param [detail]
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 操作失败返回具体描述
     * @Date 2020/7/9
     **/
    protected ResultData operationFailed(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    //TODO 代码未完成
}
