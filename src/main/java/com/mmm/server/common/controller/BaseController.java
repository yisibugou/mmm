package com.mmm.server.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class BaseController {
    protected Map<String, Object> paramMap = new HashMap<String, Object>();
    protected JSONObject resultObj = new JSONObject();

    /**
     * 用于获取Map对象
     * */
    protected Map<String,Object> getParamMap() {
        paramMap = new HashMap<>();
        return paramMap;
    }

    /**
     * 用于获取返回的JSONObject对象
     * */
    protected JSONObject getResultObj() {
        resultObj = new JSONObject();
        return resultObj;
    }
}