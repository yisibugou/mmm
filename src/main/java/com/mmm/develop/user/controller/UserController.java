package com.mmm.develop.user.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mmm.develop.common.controller.BaseController;
import com.mmm.develop.user.entity.User;
import com.mmm.develop.user.service.UserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject get(@RequestParam(value = "id") int id) {
    	paramMap = this.getParamMap();
    	resultObj = this.getResultObj();
    	paramMap.put("id", id);
        User user = userService.findOne(paramMap);
        if(user != null) {
            resultObj.put("user", user);
            resultObj.put("errCode", 0);
        }else {
            resultObj.put("errCode", 1);
        }
        return resultObj;
    }
}
