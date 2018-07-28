package com.mmm.develop.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mmm.develop.common.controller.BaseController;
import com.mmm.develop.common.util.DateFormatUtil;
import com.mmm.develop.common.util.EncryptUtil;
import com.mmm.develop.common.util.StringConvertUtil;
import com.mmm.develop.user.entity.User;
import com.mmm.develop.user.service.UserService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject get(@RequestParam(value = "id") String id) {
    	paramMap = this.getParamMap();
    	resultObj = this.getResultObj();
    	paramMap.put("id", id);
        User user = userService.findOneService(paramMap);
        if(user != null) {
            resultObj.put("data", user);
            resultObj.put("errCode", 0);//0存在
        }else {
            resultObj.put("errCode", 1);
            resultObj.put("data", "用户不存在");//1不存在
        }
        return resultObj;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody String registerInfo) {
        JSONObject reqjson=StringConvertUtil.toJSON(registerInfo);
        paramMap = this.getParamMap();
        resultObj = this.getResultObj();

        int reqType = reqjson.getInteger("reqType");
        User user = new User();
        if (reqType == 0){//0为邮箱注册，1为手机号注册
            paramMap.put("email", reqjson.getString("email"));
            user.setEmail(reqjson.getString("email"));
            resultObj.put("errCode", 2);//0注册成功，1注册失败，2用户邮箱存在，3为手机号存在,4为验证码不正确
        }else {
            paramMap.put("phone", reqjson.getString("phone"));
            user.setPhone(reqjson.getString("phone"));
            resultObj.put("errCode", 3);//0注册成功，1注册失败，2用户邮箱存在，3为手机号存在,4为验证码不正确
        }

        user.setPassword(EncryptUtil.encodeStr(reqjson.getString("password")));
        if (userService.findOneService(paramMap)==null) {
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

        paramMap.put("account", reqJson.getString("account"));
        User user = userService.loginService(paramMap);
        if (user != null){
            String password = reqJson.getString("password");
            if (EncryptUtil.checkEncodeStr(password,user.getPassword())){
                resultObj.put("errCode", 0);//0登录成功，1登录失败，2用户不存在
                Map<String, Object> para = new HashMap<>();
                para.put("id", user.getId());
                resultObj.put("user", user = userService.findOneService(para));//前端缓存id

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
