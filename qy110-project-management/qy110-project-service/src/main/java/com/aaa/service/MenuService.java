package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MenuMapper;
import com.aaa.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Joy
 * @Date: 2020/7/16 16:35
 * @Description:
 */
@Service
public class MenuService extends BaseService<Menu> {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * @param []
     * @return java.util.List<com.aaa.model.Menu>
     * @author Joy
     * @description: 查询一级菜单
     * @Date 2020/7/16
     **/
    public List<Menu> firstMenu() {
        return menuMapper.firstMenu();
    }


    /**
     * @param [id]
     * @return java.util.List<com.aaa.model.Menu>
     * @author Joy
     * @description: 根据一级菜单的ID查询二级菜单
     * @Date 2020/7/16
     **/
   public List<Menu> secondMenu(int id) {
        if (id != 0) {
            return menuMapper.secondMenu(id);
        }
        return null;
    }
}
