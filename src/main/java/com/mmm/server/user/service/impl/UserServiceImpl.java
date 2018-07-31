package com.mmm.server.user.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.mmm.server.user.entity.User;
import com.mmm.server.user.mapper.UserMapper;
import com.mmm.server.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public JSONObject insertOneService(User user) {
        JSONObject resultJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        int res = userMapper.insertSelective(user);
        if (res == 1) {
            resultJSON.put("error_code", "1");
            resultJSON.put("data", "插入成功");
        }else{
            resultJSON.put("error_code", "1");
            resultJSON.put("data", "插入失败");
        }
        return resultJSON;
    }

    @Override
    public JSONObject updateOneService(User user) {
        JSONObject resultJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        int res = userMapper.updateByPrimaryKeySelective(user);
        if (res == 1) {
            resultJSON.put("error_code", "1");
            resultJSON.put("data", "更新成功");
        }else{
            resultJSON.put("error_code", "1");
            resultJSON.put("data", "更新失败");
        }
        return resultJSON;
    }

    @Override
    public JSONObject deleteOneService(Map<String, Object> paramMap) {
        JSONObject resultJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        int res = userMapper.deleteByPrimaryKey(paramMap.get("id"));
        if (res == 1) {
            resultJSON.put("error_code", "1");
            resultJSON.put("data", "删除成功");
        }else{
            resultJSON.put("error_code", "1");
            resultJSON.put("data", "删除失败");
        }
        return resultJSON;
    }

    @Override
    public User findOneService(User user) {
        return userMapper.selectOne(user);
    }
}
