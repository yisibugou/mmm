package com.mmm.develop.user.controller;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String get(@RequestParam(value = "id") int id) {
    	Map<String, Object> paramMap = new HashMap<>();
    	paramMap.put("id", id);
        User user = userService.get(paramMap);
        if(user != null) result.put("user", user);
        return returnSuccess();
    }
}
