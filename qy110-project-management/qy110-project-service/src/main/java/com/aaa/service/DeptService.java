package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.DeptMapper;
import com.aaa.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Joy
 * @Date: 2020/7/16 15:46
 * @Description:
 */
@Service
public class DeptService extends BaseService<Dept> {
    @Autowired
    private DeptMapper deptMapper;

    /**
     * @param []
     * @return java.util.List<com.aaa.model.Dept>
     * @author Joy
     * @description: 查询出一级部门
     * @Date 2020/7/16
     **/
    public List<Dept> firstDept() {
        return deptMapper.firstDept();
    }


    /**
     * @param [id]
     * @return java.util.List<com.aaa.model.Dept>
     * @author Joy
     * @description: 根据一级部门的ID查询出对应的二级部门
     * @Date 2020/7/16
     **/
    public List<Dept> secondDept(int id) {
        if (id != 0) {
            return deptMapper.secondDept(id);
        }
        return null;
    }
}
