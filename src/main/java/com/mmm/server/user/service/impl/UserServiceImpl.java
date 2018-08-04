package com.mmm.server.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mmm.server.common.service.BaseService;
import com.mmm.server.common.util.DateFormatUtil;
import com.mmm.server.common.util.EncryptUtil;
import com.mmm.server.common.util.MailUtil;
import com.mmm.server.common.util.RedisUtil;
import com.mmm.server.user.dao.UserDao;
import com.mmm.server.user.entity.User;
import com.mmm.server.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service(value = "userService")
public class UserServiceImpl extends BaseService implements UserService{
    @Resource
    private UserDao userDao;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public JSONObject registerService(JSONObject reqJson) {
        resultObj = this.getResultObj();
        int reqType = reqJson.getInteger("reqType");
        User user = new User();

        // 判断用户是否存在
        if (reqType == 0){//0为邮箱注册，1为手机号注册
            user.setEmail(reqJson.getString("email"));
            if(userDao.findOne(user) != null) {
                resultObj.put("errCode", 2);//0注册成功，1注册失败，2用户邮箱存在，3为手机号存在,4为验证码不正确
                return resultObj;
            }
        }else {
            user.setPhone(reqJson.getString("phone"));
            if(userDao.findOne(user) != null) {
                resultObj.put("errCode", 3);
                return resultObj;
            }
        }
        // 判断验证码
        String codeRedis = (String)redisUtil.get(reqType == 0 ? reqJson.getString("email") :
                reqJson.getString("phone"));
        if(codeRedis == null || Integer.parseInt(codeRedis) != reqJson.getInteger("code")) {
            resultObj.put("errCode", 4);
            return resultObj;
        }
        user.setPassword(EncryptUtil.encodeStr(reqJson.getString("password")));
        user.setUserName("万事屋客人");
        resultObj.put("errCode", Integer.parseInt(userDao.insertOne(user).getString("error_code")));
        return  resultObj;
    }

    @Override
    public JSONObject loginService(JSONObject reqJson) {
        resultObj = this.getResultObj();
        User user = new User();
        String account = reqJson.getString("account");
        user.setUserName(account);
        user = userDao.findOne(user);
        if( user == null) {
            user = new User();
            user.setPhone(account);
            user = userDao.findOne(user);
        }
        if( user == null) {
            user = new User();
            user.setEmail(account);
            user = userDao.findOne(user);
        }
        if (user != null){
            String password = reqJson.getString("password");
            if (EncryptUtil.checkEncodeStr(password,user.getPassword())){
                resultObj.put("errCode", 0);//0登录成功，1登录失败，2用户不存在
                resultObj.put("user", user);//前端缓存id

                user.setLastLoginTime(DateFormatUtil.formatDateTime(new Date()));
                userDao.updateOne(user);
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
        user = userDao.findOne(user);
        if(user != null) {
            resultObj.put("data", user);
            resultObj.put("errCode", 0);//0存在
        }else {
            resultObj.put("errCode", 1);
            resultObj.put("data", "用户不存在");//1不存在
        }
        return resultObj;
    }

    @Override
    public JSONObject sendEmailCodeService(String email) {
        resultObj = this.getResultObj();
        int code = (int)((Math.random()*9+1)*100000);
        redisUtil.put(email, code+"", 300);
        MailUtil.sendHtmlMail(email,"万事通注册验证码","【万事通】感谢注册万事通，验证码：" +
                code+"，输入即可完成注册，此条有效时间为5分钟。如非本人发起请忽略。");
        resultObj.put("errCode", 0);
        return resultObj;
    }

    @Override
    public JSONObject isRegisteredService(JSONObject reqJson) {
        resultObj = this.getResultObj();
        int reqType = reqJson.getInteger("reqType");
        User user = new User();
        resultObj.put("errCode", 0);
        // 判断用户是否存在
        // 0为邮箱注册，1为手机号注册
        if (reqType == 0) user.setEmail(reqJson.getString("email"));
        else user.setPhone(reqJson.getString("phone"));
        if(userDao.findOne(user) != null) resultObj.put("errCode", 1);
        return resultObj;
    }
}
