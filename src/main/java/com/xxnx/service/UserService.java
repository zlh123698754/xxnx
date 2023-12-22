package com.xxnx.service;

import com.xxnx.entity.user;
import com.xxnx.entity.vo.message;
import com.xxnx.mapper.UserMapper;
import com.xxnx.util.*;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserService {
    public message userLogin(Integer school_id, String Zhanghao, String passwd) throws IOException, NoSuchAlgorithmException {
        message message = new message();
        user user = new user();
        user.setSchool_id(school_id);
        user.setZhanghao(Zhanghao);
        user.setPasswd(passwd);


        //1.判断是否为空

        if (StringUtil.isEmpty(String.valueOf(school_id)) ||StringUtil.isEmpty(String.valueOf(Zhanghao)) ){
            message.setCode(0);
            message.setMag("用户名姓名不能为空");
            return message;
        }

        String M_data = SM4.jiami(passwd,MD5.jiami("NX"+school_id+Zhanghao));



        //2.发送数据
        //3.如果为20000（成功），查询用户是否在数据库，如果不在，则写入
        //  入股code不为2000则，返回message提示密码不对
        JSONObject jsonObject = okhttp.run(school_id,M_data,Zhanghao);
       int code =  okhttp.getcode(jsonObject);
        switch (code){
            case 42004:
                message.setCode(0);
                message.setMag("账号不存在");
                return message;

            case 42002 :
                message.setCode(0);
                message.setMag("密码错误");
                return message;

            case 99999:
                message.setCode(0);
                message.setMag("一定有哪里不对");
                return message;
        }




        try (SqlSession session = GetSqlSession.createSqlSession()) {

            Integer id = okhttp.getuser_id(jsonObject);

            UserMapper userMapper = session.getMapper(UserMapper.class);
            user.setUser_id(id);
            user.setUser_name(okhttp.getuser_name(jsonObject));
            user user1 = userMapper.queryUserById(String.valueOf(id));
            if (user1==null){
                user.setM_data(M_data);
                userMapper.addUser(user);
            }
            session.commit();
        }


        //登录成功
        message.setObject(user);

        return message;
    }
}
