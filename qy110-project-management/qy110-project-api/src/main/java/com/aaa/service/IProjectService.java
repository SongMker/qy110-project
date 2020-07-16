package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/15 15:08
 * @Description:
 */
@FeignClient(value = "PROVIDER-BACKUP")
public interface IProjectService {
    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 登录操作
     * @Date 2020/7/15
     **/
    @PostMapping("/doLogin")
    ResultData doLogin(@RequestBody User user);

    /**
     * @param [loginLog]
     * @return java.lang.Integer
     * @author Joy
     * @description: 新增日志
     * @Date 2020/7/15
     **/
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLog loginLog);

    /**
     * @param [username]
     * @return java.util.List<java.util.Map>
     * @author Joy
     * @description: 动态菜单
     * @Date 2020/7/16
     **/
    @GetMapping("/allMenu")
    List<Map> selectMenu(@RequestParam(value = "username") String username);


    /**
     * @param [currentPage, pageSize]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 字典查询
     * @Date 2020/7/16
     **/
    @GetMapping("/allDict")
    ResultData allDict(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize);

    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete dict
     * @Date 2020/7/16
     **/
    @PostMapping("/delDict")
    ResultData delDict(@RequestBody Dict dict);

    /**
     * @param [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add dict
     * @Date 2020/7/16
     **/
    @PostMapping("/addDict")
    ResultData addDict(@RequestBody Dict dict);

    /**
     * @param [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update dict
     * @Date 2020/7/16
     **/
    @PostMapping("/updDict")
    ResultData updDict(@RequestBody Dict dict);

    /**
     * @param [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:
     * @Date 2020/7/16 select dict
     **/
    @PostMapping("/selectDict")
    ResultData selectDict(@RequestBody Dict dict);


    /**
     * @param []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select all users
     * @Date 2020/7/16
     **/
    @GetMapping("/allUser")
    ResultData allUser();

    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add user
     * @Date 2020/7/16
     **/
    @PostMapping("/addUser")
    ResultData addUser(@RequestBody User user);


    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete user by id
     * @Date 2020/7/16
     **/
    @PostMapping("/delUser")
    ResultData delUser(@RequestBody User user);


    /**
     * @param [user]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update user
     * @Date 2020/7/16
     **/
    @PostMapping("/updateUser")
    ResultData updUser(@RequestBody User user);


    /**
     * @param [currentPage, pageSize]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select all role by pageHelper
     * @Date 2020/7/16
     **/
    @GetMapping("/allRole")
    ResultData allRole(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize);


    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add role
     * @Date 2020/7/16
     **/
    @PostMapping("/addRole")
    ResultData addRole(@RequestBody Role role);


    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete user
     * @Date 2020/7/16
     **/
    @PostMapping("/delRole")
    ResultData delRole(@RequestBody Role role);


    /**
     * @param [role]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update user
     * @Date 2020/7/16
     **/
    @PostMapping("/updRole")
    ResultData updRole(@RequestBody Role role);


    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据角色ID获取对应的权限树
     * @Date 2020/7/16
     **/
    @GetMapping("/roleTree/{id}")
    ResultData roleTree(@PathVariable("id") int id);

    /**
     * @param []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 查询出一级部门
     * @Date 2020/7/16
     **/
    @GetMapping("/firstDept")
     ResultData firstDept();

    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据一级部门的ID查询出对应的二级部门
     * @Date 2020/7/16
     **/
    @GetMapping("/secondDept/{id}")
     ResultData secondDept(@PathVariable int id);

    @PostMapping("/delDept")
     ResultData delDept(@RequestBody Dept dept) ;

    @PostMapping("/addDept")
     ResultData addDept(@RequestBody Dept dept);

    @PostMapping("/updDept")
     ResultData updDept(@RequestBody Dept dept);


    /**
     * @param  []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:  查询一级菜单
     * @Date 2020/7/16
     **/
    @GetMapping("/firstMenu")
     ResultData firstMenu();
    /**
     * @param  [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据一级菜单ID查询二级菜单
     * @Date 2020/7/16
     **/
    @GetMapping("/secondMenu/{id}")
     ResultData secondMenu(@PathVariable int id);

    @PostMapping("/delMenu")
     ResultData delMenu(@RequestBody Menu menu) ;

    @PostMapping("/addMenu")
     ResultData addMenu(@RequestBody Menu menu);

    @PostMapping("/updMenu")
     ResultData updMenu(@RequestBody Menu menu);
}
