package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Dept;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Joy
 * @Date: 2020/7/16 15:56
 * @Description:
 */
@RestController
public class DeptController {
    @Autowired
    private IProjectService iProjectService;


    /**
     * @param []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 查询出一级部门
     * @Date 2020/7/16
     **/
    @GetMapping("/firstDept")
    ResultData firstDept() {
        return iProjectService.firstDept();
    }

    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据一级部门的ID查询出对应的二级部门
     * @Date 2020/7/16
     **/
    @GetMapping("/secondDept/{id}")
    ResultData secondDept(@PathVariable int id) {
        return iProjectService.secondDept(id);
    }

    @PostMapping("/delDept")
    ResultData delDept(@RequestBody Dept dept) {
        return iProjectService.delDept(dept);
    }

    @PostMapping("/addDept")
    ResultData addDept(@RequestBody Dept dept) {
        return iProjectService.addDept(dept);
    }

    @PostMapping("/updDept")
    ResultData updDept(@RequestBody Dept dept) {
        return iProjectService.updDept(dept);
    }
}
