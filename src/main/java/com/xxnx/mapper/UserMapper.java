package com.xxnx.mapper;

import com.xxnx.entity.user;
import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.Repeatable;
import java.util.List;


public interface UserMapper {
    user queryUserById(String user_id);

    int addUser(user user);

    int addsign(user user);

    int addbao(user user);
    user querybaoById(String user_id);
    user querysignById(String user_id);

    List<user> queryusersuoyu();
    List<user> querysignsuoyu();



}
