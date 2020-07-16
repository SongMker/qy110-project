package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Dept;
import com.aaa.service.DeptService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Joy
 * @Date: 2020/7/16 15:48
 * @Description:
 */
@RestController
public class DeptController extends CommonController<Dept> {
    @Autowired
    private DeptService deptService;

    @Override
    public BaseService<Dept> getBaseService() {
        return deptService;
    }

    /**
     * @param []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 查询出一级部门
     * @Date 2020/7/16
     **/
    @GetMapping("/firstDept")
    public ResultData firstDept() {
        List<Dept> depts = deptService.firstDept();
        if (depts != null && depts.size() > 0) {
            return super.operationSuccess(depts);
        }
        return super.operationFailed();
    }

    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据一级部门的ID查询出对应的二级部门
     * @Date 2020/7/16
     **/
    @GetMapping("/secondDept/{id}")
    public ResultData secondDept(@PathVariable int id) {
        List<Dept> depts = deptService.secondDept(id);
        if (depts != null && depts.size() > 0) {
            return super.operationSuccess(depts);
        }
        return super.operationFailed();
    }

    @PostMapping("/delDept")
    public ResultData delDept(@RequestBody Dept dept) {
        return super.deleteByT(dept);
    }

    @PostMapping("/addDept")
    public ResultData addDept(@RequestBody Dept dept) {
        return super.addByT(dept);
    }

    @PostMapping("/updDept")
    public ResultData updDept(@RequestBody Dept dept) {
        return super.updateByT(dept);
    }
}
