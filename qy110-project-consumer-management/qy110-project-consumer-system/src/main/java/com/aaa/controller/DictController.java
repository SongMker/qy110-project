package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Dict;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Joy
 * @Date: 2020/7/16 10:33
 * @Description:
 */
@RestController
public class DictController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @param [currentPage, pageSize]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: 字典查询
     * @Date 2020/7/16
     **/
    @GetMapping("/allDict")
    ResultData allDict(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        return iProjectService.allDict(currentPage, pageSize);
    }

    /**
     * @param [id]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: delete dict
     * @Date 2020/7/16
     **/
    @PostMapping("/delDict")
    ResultData delDict(@RequestBody Dict dict) {
        return iProjectService.delDict(dict);
    }


    /**
     * @param [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: add dict
     * @Date 2020/7/16
     **/
    @PostMapping("/addDict")
    ResultData addDict(@RequestBody Dict dict) {
        return iProjectService.addDict(dict);
    }

    /**
     * @param [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description: update dict
     * @Date 2020/7/16
     **/
    @PostMapping("/updDict")
    ResultData updDict(@RequestBody Dict dict) {
        return iProjectService.updDict(dict);
    }

    /**
     * @param  [dict]
     * @return com.aaa.base.ResultData
     * @author Joy
     * @description:  select dict
     * @Date 2020/7/16
     **/
    @PostMapping("/selectDict")
    public ResultData selectDict(@RequestBody Dict dict){
        return iProjectService.selectDict(dict);
    };

}
