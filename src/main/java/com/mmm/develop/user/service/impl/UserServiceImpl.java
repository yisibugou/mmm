package com.mmm.develop.user.service.impl;

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
    public User get(int id) {
        return userDao.get(id);
    }
}
