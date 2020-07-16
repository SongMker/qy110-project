package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Role;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Joy
 * @Date: 2020/7/16 15:29
 * @Description:
 */
@RestController
public class RoleController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @param [currentPage, pageSize]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select all role by pageHelper
     * @Date 2020/7/16
     **/
    @GetMapping("/allRole")
    ResultData allRole(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        return iProjectService.allRole(currentPage, pageSize);
    }


    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add role
     * @Date 2020/7/16
     **/
    @PostMapping("/addRole")
    ResultData addRole(@RequestBody Role role) {
        return iProjectService.addRole(role);
    }


    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete user
     * @Date 2020/7/16
     **/
    @PostMapping("/delRole")
    ResultData delRole(@RequestBody Role role) {
        return iProjectService.delRole(role);
    }


    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update user
     * @Date 2020/7/16
     **/
    @PostMapping("/updRole")
    ResultData updRole(@RequestBody Role role) {
        return iProjectService.updRole(role);
    }


    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据角色ID获取对应的权限树
     * @Date 2020/7/16
     **/
    @GetMapping("/roleTree/{id}")
    ResultData roleTree(@PathVariable("id") int id) {
        return iProjectService.roleTree(id);
    }
}
