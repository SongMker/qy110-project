package com.aaa.utils;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Joy
 * @Date: 2020/7/9 15:07
 * @Description:
 */
public class Map2BeanUtils {

    private Map2BeanUtils() {
    }

    //高性能实例化工具

    private final static Objenesis OBJENESIS = new ObjenesisStd(true);
    private final static StringBuilder STRING_BUILDER = new StringBuilder();
    private final static ConcurrentHashMap<Class, MethodAccess> CONCURRENT_HASH_MAP
            = new ConcurrentHashMap<Class, MethodAccess>(16);

    /**
     * map转java bean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
        //获取实例对象信息
        T instance = OBJENESIS.newInstance(clazz);
        //用过key获取MethodAccess对象
        MethodAccess methodAccess = CONCURRENT_HASH_MAP.get(clazz);
        if (null == methodAccess) {
            //通过类获取MethodAccess对象
            methodAccess = MethodAccess.get(clazz);
            CONCURRENT_HASH_MAP.putIfAbsent(clazz, methodAccess);
        }
        for (Map.Entry entry : map.entrySet()) {
            String setMethodName = getSetMethodName((String) entry.getKey());
            int index = methodAccess.getIndex(setMethodName, entry.getValue().getClass());
            methodAccess.invoke(instance, index, entry.getValue());
        }
        return instance;
    }

    /**
     * 根据字段拼接方法名
     *
     * @param filedName
     * @return
     */
    private static String getSetMethodName(String filedName) {
        STRING_BUILDER.setLength(0);
        return STRING_BUILDER.append("set").append(first2UpperCase(filedName)).toString();
    }

    /**
     * 方法首字母转换成大写
     */
    private static String first2UpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
