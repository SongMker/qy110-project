package com.aaa.base;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.aaa.utils.Map2BeanUtils;
import com.aaa.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.OrderProperties.*;

/**
 * @Author: Joy
 * @Date: 2020/7/8 15:38
 * @Description: 通用service
 */
public abstract class BaseService<T> {
    /**
     * @author Joy
     * @description: 泛型子类的泛型类型
     * @Date 2020/7/9
     **/
    private Class<T> cache = null;
    @Autowired
    private Mapper<T> mapper;

    protected Mapper getMapper() {
        return mapper;
    }

    /**
     * @param t
     * @return java.lang.Integer
     * @author Joy
     * @description: 新增
     * @Date 2020/7/9
     **/
    public Integer add(T t) {
        return mapper.insert(t);
    }

    /**
     * @param t
     * @return java.lang.Integer
     * @author Joy
     * @description:根据主键删除
     * @Date 2020/7/9
     **/
    public Integer delete(T t) {
        return mapper.deleteByPrimaryKey(t);
    }

    /**
     * @param ids
     * @return java.lang.Integer
     * @author Joy
     * @description:根据主键批量删除
     * @Date 2020/7/9
     **/
    public Integer deleteByIds(List<Integer> ids) {
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", ids)).build();
        return mapper.deleteByExample(example);
    }


    /**
     * @param t
     * @return java.lang.Integer
     * @author Joy
     * @description:根据主键修改
     * @Date 2020/7/9
     **/
    public Integer update(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @param t, ids
     * @return java.lang.Integer
     * @author Joy
     * @description:批量修改 update tableName set username=xx where id in (1,2,3,4)
     * @Date 2020/7/9
     **/
    public Integer batchUpdate(T t, Integer[] ids) {
        Example example = Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t, example);

    }

    /**
     * @param t
     * @return T
     * @author Joy
     * @description: 查询一条数据
     * @Date 2020/7/9
     **/
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    /**
     * @param where, orderFiled, fields
     * @return T
     * @author Joy
     * @description: 查询一条数据  可以带参数
     * @Date 2020/7/9
     **/
    public T selectOneByFiled(Sqls where, String orderFiled, String... fields) {
        return (T) selectByFields(null, null, where, orderFiled, null, fields);
    }

    /**
     * @param where, orderFiled, fields
     * @return java.util.List<T>
     * @author Joy
     * @description: 通过条件查询一个列表
     * @Date 2020/7/9
     **/
    public List<T> selectListByFiled(Sqls where, String orderFiled, String... fields) {
        return selectByFields(null, null, where, orderFiled, null, fields);
    }

    /**
     * @param pageNo, pageSize, where, orderFiled, fields
     * @return com.github.pagehelper.PageInfo<T>
     * @author Joy
     * @description: 分页查询
     * @Date 2020/7/9
     **/
    public PageInfo<T> selectByPageAndFiled(Integer pageNo, Integer pageSize, Sqls where, String orderFiled, String... fields) {
        return new PageInfo<T>(selectByFields(pageNo, pageSize, where, orderFiled, null, fields));
    }

    /**
     * @param t
     * @return java.util.List<T>
     * @author Joy
     * @description: 查询集合
     * @Date 2020/7/9
     **/
    public List<T> selectList(T t) {
        return mapper.select(t);
    }

    /**
     * @param t, pageNo, pageSize
     * @return com.github.pagehelper.PageInfo<T>
     * @author Joy
     * @description: 分页查询 返回一个PageInfo对象
     * @Date 2020/7/9
     **/
    public PageInfo<T> selectListPage(T t, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> tPageInfo = new PageInfo<>(select);
        return tPageInfo;
    }

    /**
     * @param
     * @return java.lang.Class<T>
     * @author Joy
     * @description: 获取子类泛型类型
     * @Date 2020/7/9
     **/
    public Class<T> getTypeArgument() {
        if (null == cache) {
            cache = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return cache;
    }

    /**
     * @param map
     * @return T
     * @author Joy
     * @description: MAP转实体类
     * @Date 2020/7/9
     **/
    public T newInstance(Map map) {
        return (T) Map2BeanUtils.map2Bean(map, getTypeArgument());
    }

    /**
     * @param orderWord, pageSize, where, orderFiled, orderWord, fields
     * @return java.util.List<T>
     * @author Joy
     * @description: 实现通用查询  可实现分页 排序 查询单个 查询所有 多条件查询
     * @Date 2020/7/9
     **/
    private List<T> selectByFields(Integer pageNo, Integer pageSize, Sqls where, String orderFiled, String orderWord, String... fields) {
        Example.Builder builder = null;
        if (null == fields || fields.length == 0) {
            //查询所有数据
            builder = Example.builder(getTypeArgument());
        } else {
            //条件查询
            builder = Example.builder(getTypeArgument()).select(fields);
        }
        if (where != null) {
            //用户自定义的where语句
            builder = builder.where(where);
        }
        if (orderFiled != null) {
            //排序
            if (DESC.equals(orderWord.toUpperCase())) {
                builder = builder.orderByDesc(orderFiled);
            } else if (ASC.equals(orderWord.toUpperCase())) {
                builder = builder.orderByAsc(orderFiled);
            } else {
                builder = builder.orderByDesc(orderFiled);
            }
        }
        Example example = builder.build();
        //分页
        if (pageNo != null & pageSize != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        return getMapper().selectByExample(example);
    }

    /**
     * 获取spring容器的上下文
     * 当项目开始运行时会加载spring的配置文
     */
    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }
}
