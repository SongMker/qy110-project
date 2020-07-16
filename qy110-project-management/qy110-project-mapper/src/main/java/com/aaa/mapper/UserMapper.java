package com.aaa.mapper;

import com.aaa.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@Repository
public interface UserMapper extends Mapper<User> {
     List<Map> allUser();
}