package com.aaa.mapper;

import com.aaa.model.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@Repository
public interface RoleMapper extends Mapper<Role> {
    /**
     * @param  [id]
     * @return java.util.List<java.util.Map>
     * @author Joy
     * @description: 根据角色Id获取权限树
     * @Date 2020/7/16
     **/
    List<Map> roleTree(int id);
}