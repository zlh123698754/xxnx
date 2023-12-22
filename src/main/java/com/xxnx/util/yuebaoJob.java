package com.xxnx.util;

import com.xxnx.entity.user;
import com.xxnx.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class yuebaoJob implements Job {
    SqlSession session = GetSqlSession.createSqlSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<user> user1 = userMapper.queryusersuoyu();
        for (user stu:user1) {
            try {



                okhttp.bao(okhttp.getToken( okhttp.run(stu.getSchool_id(),stu.getM_data(),stu.getZhanghao())),"month",huodecontent(stu.getUser_id()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public String huodecontent(int  user_id){

        user user1 = userMapper.querybaoById(String.valueOf(user_id));
        if (Objects.isNull(user1.getM1())){
            user1.setM1("今日我了解到考勤对于-个公司的重要性，它更多的是一-种约束监督。为维\n" +
                    "\n" +
                    "护良好的经营秩序，提高工作效事，保证各项工作的顺利进行，以使员工保持良好\n" +
                    "\n" +
                    "的身体素质和旺盛的精力，努力做好本职工作，我们会严格实行考勤管理。完成每\n" +
                    "\n" +
                    "月考勤记录，并根据考勤情况进行薪资计算与发放。这是相当重要的- - 块内容，计\n" +
                    "\n" +
                    "算薪资需要严谨的态度和细心的工作状态以及高度的责任感。虽然只是简单的计算，\n" +
                    "\n" +
                    "公司目前拥有的员工及人员量的增加也加大了一定的难度。");

            user1.setM2("今日在工作中仅仅能够完成布置的工作，在没有工作任务时虽能主动要求布\n" +
                    "\n" +
                    "置工作，但若没有工作做时可能就会松懈，不能做到主动学习，这主要还是因为懒\n" +
                    "\n" +
                    "情在作怪，在今后我要努力克服情性，没有工作任务时主动要求布置工作。没有布\n" +
                    "\n" +
                    "置工作时作到自主学习。");
            user1.setM3("本次实践随线上会议而暂告一段落，但追梦者的步履不停，通过此次实践，相信大家在未来自己的学习生涯也有了更好的规划，并且在今后步入社会后更好的适应社会，不断地提升自我，创造出更多的价值。教学实践虽已结束，但每个成员追梦的路上却决不停歇，永不言弃，沿着自己内心期许的方向，不断大步向前，奔向光明而又美好的未来。");
        }
        return "[{\"content\":\""+user1.getM1()+"\",\"require\":\"1\",\"sort\":\"1\",\"title\":\"实习工作具体情况及实习任务完成情况\"},{\"content\":\""+user1.getM2()+"\",\"require\":\"0\",\"sort\":\"2\",\"title\":\"主要收获及工作成绩\"},{\"content\":\""+user1.getM3()+"\",\"require\":\"0\",\"sort\":\"3\",\"title\":\"工作中的问题及需要老师的指导帮助\"}]";

    }


}