package com.aaa.base;


import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: Joy
 * @Date: 2020/7/8 15:32
 * @Description:
 */
public abstract class CommonController<T> extends BaseController {
    /**
     * 抽象方法
     *
     * @return
     */
    public abstract BaseService<T> getBaseService();

    /**
     * 钩子函数  在新增之前执行的操作
     *
     * @param map
     */
    protected void beforeAdd(Map map) {
        //TODO 增加方法执行前的执行
    }

    protected void afterAdd(Map map) {
        //TODO  增加方法执行后执行
    }

    /**
     * @param map
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 通用新增方法
     * @Date 2020/7/9
     **/
    public ResultData add(@RequestBody Map map) {
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        Integer addResult = getBaseService().add(instance);
        if (addResult > 0) {
            afterAdd(map);
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    public ResultData addByT(@RequestBody T t) {
        Integer addResult = getBaseService().add(t);
        if (addResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }


    /**
     * @param map
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 通用删除方法
     * @Date 2020/7/9
     **/

    public ResultData delete(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        Integer delete = getBaseService().delete(instance);
        if (delete > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }
    public ResultData deleteByT(@RequestBody T t) {
        Integer delete = getBaseService().delete(t);
        if (delete > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @param ids
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 批量删除
     * @Date 2020/7/9
     **/
    public ResultData batchDelete(@RequestParam("ids[]") Integer[] ids) {
        Integer integer = getBaseService().deleteByIds(Arrays.asList(ids));
        if (integer > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed("批量删除失败");
    }

    /**
     * @param map
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 单个更新
     * @Date 2020/7/9
     **/
    public ResultData update(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        Integer update = getBaseService().update(instance);
        if (update > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed("修改失败");
    }
    public ResultData updateByT(@RequestBody T t) {
        Integer update = getBaseService().update(t);
        if (update > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed("修改失败");
    }
    /**
     * @param map
     * @param ids
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 批量更新
     * @Date 2020/7/9
     **/
    public ResultData batchUpdate(@RequestBody Map map, @RequestParam("ids[]") Integer[] ids) {
        T instance = getBaseService().newInstance(map);
        Integer integer = getBaseService().batchUpdate(instance, ids);
        if (integer > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed("批量修改失败");
    }

    /**
     * @param map
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 查询单个
     * @Date 2020/7/9
     **/
    public ResultData getOne(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        T t = getBaseService().selectOne(instance);
        if (t != null) {
            return super.operationSuccess(t);
        }
        return super.operationFailed("查询失败");
    }

    public ResultData getOneByT(@RequestBody T t) {
        T t1 = getBaseService().selectOne(t);
        if (t != null) {
            return super.operationSuccess(t1);
        }
        return super.operationFailed("查询失败");
    }


    /**
     * @param orderFiled fields
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 多条件查询单个
     * @Date 2020/7/9
     **/
    public ResultData selectOneByFiled(@RequestParam("orderFiled") String orderFiled, @RequestParam("fields[]") String[] fields) {
        T t = getBaseService().selectOneByFiled(null, orderFiled, fields);
        if (t != null) {
            return super.operationSuccess(t);
        }
        return super.operationFailed("查询失败");
    }

    /**
     * @param
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 带条件查询多个
     * @Date 2020/7/9
     **/
    public ResultData selectListByFiled(@RequestParam("orderFiled") String orderFiled, @RequestParam("fields[]") String[] fields) {
        List<T> list = getBaseService().selectListByFiled(null, orderFiled, fields);
        if (list != null && list.size() > 0) {
            return super.operationSuccess(list);
        }
        return super.operationFailed("查询失败");
    }

    /**
     * @param
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 分页+多条件查询 返回PageInfo对象
     * @Date 2020/7/9
     **/
    public ResultData selectByPageAndFiled(@RequestParam("pageNo") Integer pageNo,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("orderFiled") String orderFiled,
                                           @RequestParam("fields[]") String[] fields
    ) {
        PageInfo<T> pageInfo = getBaseService().selectByPageAndFiled(pageNo, pageSize, null, orderFiled, fields);
        if (pageInfo != null) {
            return super.operationSuccess(pageInfo);

        }
        return super.operationFailed("查询失败");
    }

    /**
     * @param map
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 查询一个集合
     * @Date 2020/7/9
     **/
    public ResultData getList(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        List<T> ts = getBaseService().selectList(instance);
        if (ts != null) {
            return super.operationSuccess( ts);
        }
        return super.operationFailed("查询失败");
    }

    /**
     * @param map
     * @param pageNo
     * @param pageSize
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 带条件的分页查询
     * @Date 2020/7/9
     **/
    public ResultData getListByPage(@RequestBody Map map, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        T instance = getBaseService().newInstance(map);
        PageInfo<T> PageInfo = getBaseService().selectListPage(instance, pageNo, pageSize);
        if (PageInfo != null) {
            return super.operationSuccess( PageInfo);
        }
        return super.operationFailed("查询失败");
    }

    /**
     * [pageNo, pageSize]
     *
     * @return com.joy.base.ResultData
     * @author Joy
     * @description: 分页查询
     * @Date 2020/7/10
     **/
    public ResultData getListByPage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        PageInfo<T> pageInfo = getBaseService().selectListPage(null, pageNo, pageSize);
        List<T> resultList = pageInfo.getList();
        if (resultList.size() > 0) {
            return operationSuccess(pageInfo);
        }
        return operationFailed("未找到查询结果");
    }

    /**
     * @param []
     * @return javax.servlet.http.HttpServletRequest
     * @author Joy
     * @description: 从本地当前线程获取request
     * @Date 2020/7/10
     **/
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @param []
     * @return javax.servlet.http.HttpSession
     * @author Joy
     * @description: 获取客户端session对象 如果没有就创建一个
     * @Date 2020/7/10
     **/
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

    /**
     * @param []
     * @return javax.servlet.http.HttpSession
     * @author Joy
     * @description: 获取客户端session对象，如果没有返回null
     * @Date 2020/7/10
     **/
    public HttpSession getExistSession() {
        return getServletRequest().getSession(false);
    }

}
