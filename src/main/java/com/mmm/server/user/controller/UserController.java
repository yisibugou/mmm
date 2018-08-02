package com.mmm.server.user.controller;

import com.alibaba.fastjson.JSONObject;

import com.mmm.server.common.util.StringConvertUtil;
import com.mmm.server.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController{
    @Resource
    private UserService userService;

    @GetMapping(value = "/get")
    public JSONObject get(@RequestParam(value = "id") long id) {
        return userService.getService(id);
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public JSONObject register(@RequestBody String registerInfo) {
        JSONObject reqJson = StringConvertUtil.toJSON(registerInfo);
        return userService.registerService(reqJson);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public JSONObject login(@RequestBody String loginInfo) {
        JSONObject reqJson = StringConvertUtil.toJSON(loginInfo);
        return userService.loginService(reqJson);
    }
}
