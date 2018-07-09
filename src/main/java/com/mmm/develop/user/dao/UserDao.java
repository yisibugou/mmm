package com.mmm.develop.user.dao;

import com.mmm.develop.common.dao.BaseDao;
import com.mmm.develop.user.entity.User;

import java.util.Map;

public interface UserDao extends BaseDao<User>{
    User login(Map<String,Object> paramMap);
}
