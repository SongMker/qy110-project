package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Menu;
import com.aaa.service.MenuService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Joy
 * @Date: 2020/7/16 16:37
 * @Description:
 */
@RestController
public class MenuController extends CommonController<Menu> {
    @Autowired
    private MenuService menuService;

    @Override
    public BaseService<Menu> getBaseService() {
        return menuService;
    }
    /**
     * @param  []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:  查询一级菜单
     * @Date 2020/7/16
     **/
    @GetMapping("/firstMenu")
    public ResultData firstMenu() {
        List<Menu> menus = menuService.firstMenu();
        if (menus != null && menus.size() > 0) {
            return operationSuccess(menus);
        }
        return operationFailed();
    }
    /**
     * @param  [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据一级菜单ID查询二级菜单
     * @Date 2020/7/16
     **/
    @GetMapping("/secondMenu/{id}")
    public ResultData secondMenu(@PathVariable int id) {
        List<Menu> menus = menuService.secondMenu(id);
        if (menus != null && menus.size() > 0) {
            return operationSuccess(menus);
        }
        return operationFailed();
    }

    @PostMapping("/delMenu")
    public ResultData delMenu(@RequestBody Menu menu) {
        return super.deleteByT(menu);
    }

    @PostMapping("/addMenu")
    public ResultData addMenu(@RequestBody Menu menu) {
        return super.addByT(menu);
    }

    @PostMapping("/updMenu")
    public ResultData updMenu(@RequestBody Menu menu) {
        return super.updateByT(menu);
    }
}
