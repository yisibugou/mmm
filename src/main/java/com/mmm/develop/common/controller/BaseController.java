package com.mmm.develop.common.controller;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
public class BaseController {
    protected Map<String, Object> result = new HashMap<>();

    private String returnResult() {
        return JSON.toJSONString(result);
    }
    public String returnSuccess() {
        result.put("success", true);
        return returnResult();
    }
}