package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Dict;
import com.aaa.service.DictService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


/**
 * @Author: Joy
 * @Date: 2020/7/16 10:23
 * @Description:
 */
@RestController
public class DictController extends CommonController<Dict> {
    @Autowired
    private DictService dictService;

    @Override
    public BaseService<Dict> getBaseService() {
        return dictService;
    }

    /**
     * @param [currentPage, pageSize]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select all dict
     * @Date 2020/7/16
     **/
    @GetMapping("/allDict")
    public ResultData allDict(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        return super.getListByPage(currentPage, pageSize);
    }

    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete dict
     * @Date 2020/7/16
     **/
    @PostMapping("/delDict")
    public ResultData delDict(@RequestBody Dict dict) {
        return super.deleteByT(dict);
    }

    /**
     * @param [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add dict
     * @Date 2020/7/16
     **/
    @PostMapping("/addDict")
    public ResultData addDict(@RequestBody Dict dict) {
        return super.addByT(dict);
    }

    /**
     * @param [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update dict
     * @Date 2020/7/16
     **/
    @PostMapping("/updDict")
    public ResultData updDict(@RequestBody Dict dict) {
        return super.updateByT(dict);
    }
    /**
     * @param  [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: select dict
     * @Date 2020/7/16
     **/
    @PostMapping("/selectDict")
    public ResultData selectDict(@RequestBody Dict dict){
        return super.getOneByT(dict);
    }
}
