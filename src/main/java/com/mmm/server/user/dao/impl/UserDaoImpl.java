package com.mmm.server.user.dao.impl;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.mmm.server.user.entity.User;
import com.mmm.server.user.mapper.UserMapper;
import com.mmm.server.user.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userDao")
public class UserDaoImpl implements UserDao {
    @Resource
    private UserMapper userMapper;

    @Override
    public JSONObject insertOne(User user) {
        JSONObject resultJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        int res = userMapper.insertSelective(user);
        if (res == 1) {
            resultJSON.put("error_code", "0");
        }else{
            resultJSON.put("error_code", "1");
        }
        return resultJSON;
    }

    @Override
    public JSONObject updateOne(User user) {
        JSONObject resultJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        int res = userMapper.updateByPrimaryKeySelective(user);
        if (res == 1) {
            resultJSON.put("error_code", "0");
        }else{
            resultJSON.put("error_code", "1");
        }
        return resultJSON;
    }

    @Override
    public JSONObject deleteOne(Map<String, Object> paramMap) {
        JSONObject resultJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        int res = userMapper.deleteByPrimaryKey(paramMap.get("id"));
        if (res == 1) {
            resultJSON.put("error_code", "0");
        }else{
            resultJSON.put("error_code", "1");
        }
        return resultJSON;
    }

    @Override
    public User findOne(User user) {
        return userMapper.selectOne(user);
    }
}
