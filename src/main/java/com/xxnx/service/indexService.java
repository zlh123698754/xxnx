package com.xxnx.service;

import com.xxnx.entity.user;
import com.xxnx.entity.vo.message;
import com.xxnx.mapper.UserMapper;
import com.xxnx.util.GetSqlSession;
import com.xxnx.util.JinWeiDuFengGe;
import com.xxnx.util.MySchedule;
import com.xxnx.util.okhttp;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.cert.CertificateRevokedException;
import java.util.Objects;

public class indexService {
    public message userindex(String m1,String m2 ,String m3,String jinwei,String gsname,String dzname,int user_id ) {
        message message = new message();
        String[] jw;
        user user = new user();
        user.setM1(m1);
        user.setM2(m2);
        user.setM3(m3);
        if (jinwei == null | jinwei=="") {
            jw = JinWeiDuFengGe.fengge("106.156566,38.415312");
        }else {
            jw = JinWeiDuFengGe.fengge(jinwei);
        }

        user.setLongitude(jw[0]);
        user.setLatitude(jw[1]);
        user.setAddress(gsname);
        user.setAddress_name(dzname);
        user.setUser_id(user_id);


        UserMapper mapper = null;
        try (SqlSession session = GetSqlSession.createSqlSession()) {
            mapper = session.getMapper(UserMapper.class);
            user user1 = mapper.querybaoById(String.valueOf(user_id));
            if (Objects.isNull(user1)) {
                mapper.addbao(user);
            }

            user1 = mapper.querysignById(String.valueOf(user_id));
            if (Objects.isNull(user1)) {
                mapper.addsign(user);
            }

            session.commit();
        } catch (NullPointerException err) {
            SqlSession session = GetSqlSession.createSqlSession();
            mapper = session.getMapper(UserMapper.class);
            mapper.addbao(user);
            mapper.addsign(user);
        }


        return message;
    }
}
