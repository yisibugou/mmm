package com.mmm.develop.user.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mmm.develop.user.dao.UserDao;
import com.mmm.develop.user.entity.User;
import com.mmm.develop.user.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public JSONObject insertOneService(User user) {
        JSONObject resultJSON = new JSONObject();
        JSONObject dataJSON = new JSONObject();
        int res = userDao.insertOne(user);
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
        int res = userDao.updateOne(user);
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
    public JSONObject deleteOneService(User user) {
        return null;
    }

    @Override
    public User findOneService(Map<String, Object> paramMap) {
        return userDao.findOne(paramMap);
    }

    @Override
    public List<User> findMultiService(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public User loginService(Map<String, Object> paramMap) {
        return userDao.login(paramMap);
    }
}
