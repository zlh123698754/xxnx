package com.xxnx.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String jiami(String account) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(account.getBytes());
        String hashedPwd = new BigInteger(1, md.digest()).toString(16);
        // 使用substring方法截取前16位
        String subStr = hashedPwd.substring(0, 16);
        System.out.println(subStr);
        return subStr;
    }
}
