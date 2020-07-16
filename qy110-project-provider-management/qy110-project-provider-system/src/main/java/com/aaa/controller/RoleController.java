package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Role;
import com.aaa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/16 15:07
 * @Description:
 */
@RestController
public class RoleController extends CommonController<Role> {
    @Autowired
    private RoleService roleService;

    @Override
    public BaseService<Role> getBaseService() {
        return roleService;
    }

    /**
     * @param [currentPage, pageSize]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select all role by pageHelper
     * @Date 2020/7/16
     **/
    @GetMapping("/allRole")
    public ResultData allRole(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        return getListByPage(currentPage, pageSize);
    }

    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add role
     * @Date 2020/7/16
     **/
    @PostMapping("/addRole")
    public ResultData addRole(@RequestBody Role role) {
        return addByT(role);
    }

    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete user
     * @Date 2020/7/16
     **/
    @PostMapping("/delRole")
    public ResultData delRole(@RequestBody Role role) {
        return deleteByT(role);
    }

    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update user
     * @Date 2020/7/16
     **/
    @PostMapping("/updRole")
    public ResultData updRole(@RequestBody Role role) {
        return updateByT(role);
    }
    /**
     * @param  [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:  根据角色ID获取对应的权限树
     * @Date 2020/7/16
     **/
    @GetMapping("/roleTree/{id}")
    public ResultData roleTree(@PathVariable("id") int id) {
        List<Map> maps = roleService.roleTree(id);
        if (maps != null && maps.size() > 0) {
            return operationSuccess(maps);
        }
        return operationFailed();
    }

}
