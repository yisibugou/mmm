package com.mmm.develop.user.service;

import java.util.Map;

import com.mmm.develop.common.service.BaseService;
import com.mmm.develop.user.entity.User;

public interface UserService extends BaseService<User>{
    User loginService(Map<String,Object> paramMap);
}
