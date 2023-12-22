package com.xxnx.test;

import com.xxnx.entity.user;
import com.xxnx.mapper.UserMapper;
import com.xxnx.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        user user = userMapper.queryUserById("463920");
        if (user==null){
            System.out.println("困");
        }else {
            System.out.println(6);
        }


       // okhttp.run(287,"6JSCeiT5ymYeXQxP1zsdkg%3D%3D",2110604205);

    //   MD5.jiami("NX2872110604250");
    }
}
