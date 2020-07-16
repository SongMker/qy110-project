package com.aaa.redis;

import com.aaa.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;
import tk.mybatis.mapper.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.aaa.staticproperties.RedisProperties.*;


/**
 * @Author: Joy
 * @Date: 2020/7/10 9:01
 * @Description:
 */
//@Service
public class RedisService<T> {
    private RedisSerializer keySerializer = null;

    /**
     * @param []
     * @return void
     * @author Joy
     * @description: 初始化redis序列化器
     * @Date 2020/7/10
     **/
    @PostConstruct
    public void initRedisSerializer() {
        if (this.keySerializer == null) {
            keySerializer = new JdkSerializationRedisSerializer(this.getClass().getClassLoader());
        }
    }

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * @param key, value, nxxx, expx, seconds]
     * @return java.lang.String
     * @author Joy
     * @description: nxxx:两个固定值，nx ：key不存在就存 xx：key存在直接覆盖
     * expe:两个固定值，ex：失效时间是秒   px：失效时间单位是毫秒
     * seconds:失效时间
     * @Date 2  发，020/7/10
     **/
    public String set(String key, T value, String nxxx, String expx, Integer seconds) {
        if (null != seconds && 0 < seconds &&
                (EX.equals(expx) || PX.equals(expx)) &&
                (XX.equals(nxxx) || NX.equals(nxxx))) {
            //存入数据有失效时间
            return jedisCluster.set(key, JSONUtils.toJsonString(value), nxxx, expx, seconds);
        } else {
            if (NX.equals(nxxx)) {
                return String.valueOf(jedisCluster.setnx(key, JSONUtils.toJsonString(value)));
            } else if (XX.equals(nxxx)) {
                return jedisCluster.set(key, JSONUtils.toJsonString(value));
            }
        }
        return NO;
    }

    /**
     * @param [key]
     * @return T
     * @author Joy
     * @description: 从redis中查询数据 只能查询一个
     * @Date 2020/7/10
     **/
    public T getOne(String key) {
        return (T) JSONUtils.toObject(jedisCluster.get(key), Object.class);
    }

    /**
     * @param [key]
     * @return java.lang.String
     * @author Joy
     * @description: 从redis查询数据  查询字符串
     * @Date 2020/7/10
     **/
    public String getString(String key) {
        return jedisCluster.get(key);
    }

    /**
     * @param [key]
     * @return java.util.List<T>
     * @author Joy
     * @description: 从redis中查询数据 集合
     * @Date 2020/7/10
     **/
    public List<T> getList(String key) {
        return (List<T>) JSONUtils.toList(jedisCluster.get(key), Object.class);
    }

    /**
     * @param [key]
     * @return java.lang.Long
     * @author Joy
     * @description: 删除一个
     * @Date 2020/7/10
     **/
    public Long delOne(Object key) {
        return jedisCluster.del(rawKey(key));
    }

    /**
     * @param [keys]
     * @return java.lang.Long
     * @author Joy
     * @description: 删除多个
     * @Date 2020/7/10
     **/
    public Long delMany(Collection<T> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return 0L;
        } else {
            byte[][] bytes = rawKeys(keys);
            return jedisCluster.del(bytes);
        }

    }

    private byte[] rawKey(Object key) {
        //断言
        Assert.notNull(key, "这个key不存在");
//        if (keySerializer == null && key instanceof byte[]) {
//            return (byte[]) key;
//        } else {
//            //需要转换
//            return keySerializer.serialize(key);
//        }
        //三元表达式
        return this.keySerializer == null && key instanceof byte[] ?
                (byte[]) key : this.keySerializer.serialize(key);
    }

    /**
     * @param
     * @return byte[][]
     * @author Joy
     * @description: 可变参数转二维数组
     * @Date 2020/7/10
     **/
    private byte[][] rawKeys(Collection<T> keys) {
        byte[][] rks = new byte[keys.size()][];
        int i = 0;
        Object key;
        for (Iterator i9 = keys.iterator(); i9.hasNext(); rks[i++] = this.rawKey(key)) {
            key = i9.next();
        }
        return rks;
    }

}
