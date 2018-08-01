package com.mmm.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mmm.server.common.service.BaseService;
import com.mmm.server.common.util.DateFormatUtil;
import com.mmm.server.common.util.EncryptUtil;
import com.mmm.server.user.dao.UserDao;
import com.mmm.server.user.entity.User;
import com.mmm.server.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service(value = "userService")
public class UserServiceImpl extends BaseService implements UserService{
    @Resource
    private UserDao userService;

    @Override
    public JSONObject registerService(JSONObject reqJson) {
        resultObj = this.getResultObj();
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
        if (userService.findOne(user)==null) {
            resultObj = userService.insertOne(user);
        }else {
            resultObj.put("data", "用户已存在");
        }

        return  resultObj;
    }

    @Override
    public JSONObject loginService(JSONObject reqJson) {
        resultObj = this.getResultObj();
        User user = new User();
        String account = reqJson.getString("account");
        user.setUserName(account);
        user = userService.findOne(user);
        if( user == null) {
            user = new User();
            user.setPhone(account);
            user = userService.findOne(user);
        }
        if( user == null) {
            user = new User();
            user.setEmail(account);
            user = userService.findOne(user);
        }
        if (user != null){
            String password = reqJson.getString("password");
            if (EncryptUtil.checkEncodeStr(password,user.getPassword())){
                resultObj.put("errCode", 0);//0登录成功，1登录失败，2用户不存在
                resultObj.put("user", user);//前端缓存id

                user.setLastLoginTime(DateFormatUtil.formatDateTime(new Date()));
                userService.updateOne(user);
            }else {
                resultObj.put("errCode", 1);//0登录成功，1登录失败，2用户不存在
            }
        }else{
            resultObj.put("errCode", 2);//0登录成功，1登录失败，2用户不存在
        }

        return resultObj;
    }

    @Override
    public JSONObject getService(long id) {
        resultObj = this.getResultObj();
        User user = new User();
        user.setId(id);
        user = userService.findOne(user);
        if(user != null) {
            resultObj.put("data", user);
            resultObj.put("errCode", 0);//0存在
        }else {
            resultObj.put("errCode", 1);
            resultObj.put("data", "用户不存在");//1不存在
        }
        return resultObj;
    }
}
