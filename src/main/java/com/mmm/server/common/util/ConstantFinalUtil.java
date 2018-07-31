package com.mmm.server.common.util;

import java.util.ResourceBundle;

public class ConstantFinalUtil {
    //全局随机数
    public static String RANDOMSTR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // 获取properties属性文件信息,只需获取属性文件名称即可
    public static ResourceBundle BUNDLE = ResourceBundle.getBundle("application");
}
