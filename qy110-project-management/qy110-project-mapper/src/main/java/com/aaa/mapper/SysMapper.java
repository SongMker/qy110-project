package com.aaa.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/16 8:36
 * @Description:
 */
@Repository
public interface SysMapper {
    List<Map> selectMenu(String username);
}
