package com.mmm.develop.user.dao;

import com.mmm.develop.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User get(int id);
}
