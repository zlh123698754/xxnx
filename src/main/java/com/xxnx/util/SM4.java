package com.xxnx.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class SM4 {


        public static String jiami(String accout, String Key){
            SymmetricCrypto sm4 = SmUtil.sm4(Key.getBytes());
            String encryptHex = sm4.encryptBase64(accout);
           // String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
            return encryptHex;
        }
}
