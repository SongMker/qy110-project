package com.aaa.mapper;

import com.aaa.model.Menu;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MenuMapper extends Mapper<Menu> {
    /**
     * @param  []
     * @return java.util.List<com.aaa.model.Menu>
     * @author Joy
     * @description: 查询一级菜单
     * @Date 2020/7/16
     **/
    List<Menu> firstMenu();

    /**
     * @param  [id]
     * @return java.util.List<com.aaa.model.Menu>
     * @author Joy
     * @description:  根据一级菜单的ID查询二级菜单
     * @Date 2020/7/16
     **/
    List<Menu> secondMenu(int id);
}