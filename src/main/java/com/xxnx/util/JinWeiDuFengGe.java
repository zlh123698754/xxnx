package com.xxnx.util;

public class JinWeiDuFengGe {
    public static String[] fengge(String jw){
        String[] temp;
        String delimeter = ",";  // 指定分割字符
        temp = jw.split(delimeter); // 分割字符串
        // 普通 for 循环
        return temp;

    }
}
