package com.mmm.develop.user.service.impl;

import java.util.List;
import java.util.Map;

import com.mmm.develop.user.dao.UserDao;
import com.mmm.develop.user.entity.User;
import com.mmm.develop.user.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User get(Map<String, Object> paramMap) {
        return userDao.findOne(paramMap);
    }

    @Override
    public int insertOne(User user) {
        return 0;
    }

    @Override
    public int updateOne(User user) {
        return 0;
    }

    @Override
    public int deleteOne(User user) {
        return 0;
    }

    @Override
    public User findOne(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public List<User> findMulti(Map<String, Object> paramMap) {
        return null;
    }
}
