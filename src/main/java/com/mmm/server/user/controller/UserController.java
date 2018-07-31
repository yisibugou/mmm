package com.mmm.server.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import com.mmm.server.common.controller.BaseController;
import com.mmm.server.common.util.DateFormatUtil;
import com.mmm.server.common.util.EncryptUtil;
import com.mmm.server.common.util.MailUtil;
import com.mmm.server.common.util.StringConvertUtil;
import com.mmm.server.user.entity.User;
import com.mmm.server.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/get")
    public JSONObject get(@RequestParam(value = "id") long id) {
    	paramMap = this.getParamMap();
    	resultObj = this.getResultObj();
        User user = new User();
        user.setId(id);
        user = userService.findOneService(user);
        if(user != null) {
            resultObj.put("data", user);
            resultObj.put("errCode", 0);//0存在
        }else {
            resultObj.put("errCode", 1);
            resultObj.put("data", "用户不存在");//1不存在
        }
        return resultObj;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public JSONObject register(@RequestBody String registerInfo) {
        paramMap = this.getParamMap();
        resultObj = this.getResultObj();
        JSONObject reqJson = StringConvertUtil.toJSON(registerInfo);
        int reqType = reqJson.getInteger("reqType");
        User user = new User();
        if (reqType == 0){//0为邮箱注册，1为手机号注册
            user.setEmail(reqJson.getString("email"));
            resultObj.put("errCode", 2);//0注册成功，1注册失败，2用户邮箱存在，3为手机号存在,4为验证码不正确
        }else {
            user.setPhone(reqJson.getString("phone"));
            resultObj.put("errCode", 3);//0注册成功，1注册失败，2用户邮箱存在，3为手机号存在,4为验证码不正确
        }

        user.setPassword(EncryptUtil.encodeStr(reqJson.getString("password")));
        if (userService.findOneService(user)==null) {
            resultObj = userService.insertOneService(user);
        }else {
            resultObj.put("data", "用户已存在");
        }
        return resultObj;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody String loginInfo) {
        JSONObject reqJson = StringConvertUtil.toJSON(loginInfo);
        paramMap = this.getParamMap();
        resultObj = this.getResultObj();

        User user = new User();
        String account = reqJson.getString("account");
        user.setUserName(account);
        user = userService.findOneService(user);
        if( user == null ) {
            user = new User();
            user.setPhone(account);
            user = userService.findOneService(user);
        }
        if( user == null ) {
            user = new User();
            user.setEmail(account);
            user = userService.findOneService(user);
        }
        if (user != null){
            String password = reqJson.getString("password");
            if (EncryptUtil.checkEncodeStr(password,user.getPassword())){
                resultObj.put("errCode", 0);//0登录成功，1登录失败，2用户不存在
                resultObj.put("user", user);//前端缓存id

                user.setLastLoginTime(DateFormatUtil.formatDateTime(new Date()));
                userService.updateOneService(user);
            }else {
                resultObj.put("errCode", 1);//0登录成功，1登录失败，2用户不存在
            }
        }else{
            resultObj.put("errCode", 2);//0登录成功，1登录失败，2用户不存在
        }
        return resultObj;
    }
}
