package com.mmm.server.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class StringConvertUtil {

    /**
     * 截取前台请求字符串中多余的=
     * */
    public static String subReqStr(String str) {
        String temp = str.trim();
        return temp.substring(0, temp.lastIndexOf("}") + 1);
    }

    /**
     * 转换json
     * */
    public static JSONObject toJSON(String str) {
        String userInfo = UrlCoderUtil.getURLDecoderString(str);
        userInfo = subReqStr(userInfo);
        JSONObject json = JSON.parseObject(userInfo);
        return json;
    }
}
