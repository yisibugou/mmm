package com.mmm.develop.user.service.impl;

import java.util.List;
import java.util.Map;

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
    public int insertOne(User user) {
        return userDao.insertOne(user);
    }

    @Override
    public int updateOne(User user) {
        return userDao.updateOne(user);
    }

    @Override
    public int deleteOne(User user) {
        return userDao.updateOne(user);
    }

    @Override
    public User findOne(Map<String, Object> paramMap) {
        return userDao.findOne(paramMap);
    }

    @Override
    public List<User> findMulti(Map<String, Object> paramMap) {
        return null;
    }
}
