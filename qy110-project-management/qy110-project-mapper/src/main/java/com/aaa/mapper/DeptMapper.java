package com.aaa.mapper;

import com.aaa.model.Dept;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DeptMapper extends Mapper<Dept> {
    /**
     * @param  []
     * @return java.util.List<com.aaa.model.Dept>
     * @author Joy
     * @description:  查询出一级部门
     * @Date 2020/7/16
     **/
    List<Dept> firstDept();
    /**
     * @param  [id]
     * @return java.util.List<com.aaa.model.Dept>
     * @author Joy
     * @description: 根据一级部门的ID查询出对应的二级部门
     * @Date 2020/7/16
     **/
    List<Dept> secondDept(int id);
}
