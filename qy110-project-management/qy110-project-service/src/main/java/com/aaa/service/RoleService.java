package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.RoleMapper;
import com.aaa.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/16 15:07
 * @Description:
 */
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    private RoleMapper roleMapper;

    public List<Map> roleTree(int id) {
        if (id != 0) {
            return roleMapper.roleTree(id);
        }
        return null;
    }
}
