package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Menu;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Joy
 * @Date: 2020/7/16 16:44
 * @Description:
 */
@RestController
public class MenuController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @param []
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 查询一级菜单
     * @Date 2020/7/16
     **/
    @GetMapping("/firstMenu")
    ResultData firstMenu() {
        return iProjectService.firstMenu();
    }

    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 根据一级菜单ID查询二级菜单
     * @Date 2020/7/16
     **/
    @GetMapping("/secondMenu/{id}")
    ResultData secondMenu(@PathVariable int id) {
        return iProjectService.secondMenu(id);
    }

    @PostMapping("/delMenu")
    ResultData delMenu(@RequestBody Menu menu) {
        return iProjectService.delMenu(menu);
    }

    @PostMapping("/addMenu")
    ResultData addMenu(@RequestBody Menu menu) {
        return iProjectService.addMenu(menu);
    }

    @PostMapping("/updMenu")
    ResultData updMenu(@RequestBody Menu menu) {
        return iProjectService.updMenu(menu);
    }
}
