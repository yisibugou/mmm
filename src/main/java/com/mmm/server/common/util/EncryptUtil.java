package com.mmm.server.common.util;

import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 * */
public class EncryptUtil {
    /**
     * 用于生成随机数
     * @return 生成的字符串
     * */
    public static String productCode(int length) {
        StringBuffer sb=new StringBuffer();
        Random random=new Random();
        for(int i=0;i<length;i++) {
            int index=random.nextInt(ConstantFinalUtil.RANDOMSTR.length());
            char ch=ConstantFinalUtil.RANDOMSTR.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }
    /**
     * 进行加密操作
     * @return 加密后的字符串
     * */
    public static String encodeStr(String sourcePass) {
        String encType="sha256";
        //32位随机字符串
        String randStr=productCode(32);
        //拼装前缀
        String prefixStr=encType+"$"+randStr+"$";
        //真正待加密字符串
        String beforeEncodeStr=prefixStr+sourcePass;
        //加密后字符串
        String afterEncodeStr=DigestUtils.sha256Hex(beforeEncodeStr);
        afterEncodeStr=prefixStr+afterEncodeStr;
        return afterEncodeStr;
    }

    /**
     * 验证是否正确
     * @param sourceStr:为加密前的源字符串
     * @param encodeStr:为待验证的字符串
     * */
    public static boolean checkEncodeStr(String sourceStr,String encodeStr) {
        String str[]=encodeStr.split("\\$");
        String prefixStr=str[0]+"$"+str[1]+"$";
        String beforeEncodeStr=prefixStr+sourceStr;
        String afterEncodeStr=DigestUtils.sha256Hex(beforeEncodeStr);
        afterEncodeStr=prefixStr+afterEncodeStr;
        return afterEncodeStr.equalsIgnoreCase(encodeStr);
    }
}
