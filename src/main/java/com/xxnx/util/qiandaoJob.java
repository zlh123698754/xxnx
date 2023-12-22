package com.xxnx.util;

import com.xxnx.entity.user;
import com.xxnx.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class qiandaoJob implements Job {
    SqlSession session = GetSqlSession.createSqlSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        List<user> user1 = userMapper.queryusersuoyu();
        for (user stu:user1) {
            try {
                System.out.println("签到");
               user user =  userMapper.querysignById(String.valueOf(stu.getUser_id()));
               if (Objects.isNull(user.getLatitude())){
                   user.setLatitude(String.valueOf(38.389367));
                   user.setLongitude(String.valueOf(106.282515));
               }
               if (Objects.isNull(user.getAddress())){
                   user.setAddress("奈雪的茶");
                   user.setAddress_name("宁夏回族自治区银川金凤区万达广场115号");
               }
                okhttp.qiandao(okhttp.getToken( okhttp.run(stu.getSchool_id(),stu.getM_data(),stu.getZhanghao())),
                        user.getLongitude(),user.getLatitude(),user.getAddress(),user.getAddress_name());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

    }




}
