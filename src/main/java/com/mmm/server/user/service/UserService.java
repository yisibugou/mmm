package com.mmm.server.user.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {
    JSONObject registerService( JSONObject reqJson);
    JSONObject loginService( JSONObject reqJson);
    JSONObject getService(long id);
}
