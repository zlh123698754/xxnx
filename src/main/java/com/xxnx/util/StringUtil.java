package com.xxnx.util;

public class StringUtil {
    public static boolean isEmpty(String str){//判断字符串为空，如果为空返回true 如果不为空返回false
        return str == null || "".equals(str.trim());
    }
}
