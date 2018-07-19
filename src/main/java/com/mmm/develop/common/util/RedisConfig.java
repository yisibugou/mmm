package com.mmm.develop.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */
@Service
public class RedisConfig {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 往reids里面存json数据
     * @param key 要合理存放数据
     * @param valueJSON
     * @return boolean
     * */
    public boolean put(final String key,final String valueJSON) {
        Boolean result=false;
        result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection conn)
                    throws DataAccessException {
                byte[] keyBye=redisTemplate.getStringSerializer().serialize(key);
                conn.set(keyBye,redisTemplate.getStringSerializer().serialize(valueJSON));
                return true;
            }
        });
        return result;
    }

    /**
     * 往reids里面json数据,并设置过期时间
     * @param key 要合理存放数据
     * @param valueJSON
     * @param timeOut 过期时间,单位是秒
     * @return boolean
     * */
    public boolean put(final String key,final String valueJSON,final int timeOut) {
        return (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
                connection.set(keyByte, redisTemplate.getStringSerializer().serialize(valueJSON));
                //设置一下过期时间
                boolean flag=connection.expire(keyByte,timeOut);
                return flag;
            }
        });
    }

    /**
     * 往reids里面存json数据,并设置过期日期
     * @param key 要合理存放数据
     * @param valueJSON
     * @param expDate 旋转的是指定的日期过期
     * @return boolean
     * */
    public boolean put(final String key,final String valueJSON,final Date expDate) {
        return (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @SuppressWarnings("deprecation")
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException
            {
                byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
                connection.set(keyByte, redisTemplate.getStringSerializer().serialize(valueJSON));
                //设置一下过期时间
                boolean flag=connection.expireAt(keyByte, expDate.getTime());
                return flag;
            }
        });
    }

    /**
     * 缓存List数据
     * @param key        缓存的键值
     * @param dataInfo    待缓存的数据
     * @param timeOut 过期时间,单位是秒
     * @return            缓存的结果对象
     */
    public Boolean putCacheList(final String key,final String dataInfo,final int timeOut,final boolean putFlag) {
        return (Boolean) this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException
            {
                byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
                byte[] dataInfoByte=redisTemplate.getStringSerializer().serialize(dataInfo);
                //左入队列
                if(putFlag) {
                    connection.lPush(keyByte, dataInfoByte);
                }else {
                    connection.rPush(keyByte, dataInfoByte);
                }
                //设置一下过期时间
                boolean flag=connection.expire(keyByte,timeOut);
                return flag;
            }
        });
    }

    /**
     * 获得缓存的list对象
     * @param key  缓存的键值
     * @return   缓存键值对应的数据
     */
    public JSONArray getCacheList(final String key,final long startPos,final long endPos) {
        return this.redisTemplate.execute(new RedisCallback<JSONArray>() {
            @Override
            public JSONArray doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
                if(connection.exists(keyByte)) {
                    List<byte[]> list=connection.lRange(keyByte, startPos, endPos);
                    if(null!=list) {
                        JSONArray dataArray=new JSONArray();
                        for(byte[] b:list) {
                            String str=redisTemplate.getStringSerializer().deserialize(b);
                            dataArray.add(JSON.parseObject(str));
                        }
                        return dataArray;
                    }
                }
                return null;
            }
        });
    }

    /**
     * 获取队列长度
     * @param key  缓存的键值
     * @return   缓存键值对应的数据
     * */
    public Long getLength(final String key) {
        return this.redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection)throws DataAccessException {
                byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
                if(connection.exists(keyByte)) {
                    return connection.lLen(keyByte);
                }
                return null;
            }
        });
    }

    /**
     * 从redis中取数据
     * @param key
     * @return 会返回null
     * */
    public Object get(final String key) {
        return this.redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
                if(connection.exists(keyByte)) {
                    byte[] valueByte=connection.get(keyByte);
                    return redisTemplate.getStringSerializer().deserialize(valueByte);
                }
                return null;
            }
        });
    }

    /**
     * 从redis中删除数据
     * @param key
     * @return 返回删除的条数 -1为键不存在
     * */
    public Object delete(final String key) {
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                //先系列化,然后再查找是否有存在
                byte[] keyByte=redisTemplate.getStringSerializer().serialize(key);
                //存在该键,则进行删除操作
                if(connection.exists(keyByte)) {
                    long keyLon=connection.del(keyByte);
                    return keyLon;
                }
                return null;
            }
        });
    }
}