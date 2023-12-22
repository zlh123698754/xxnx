import com.xxnx.entity.user;
import com.xxnx.mapper.UserMapper;
import com.xxnx.util.GetSqlSession;
import com.xxnx.util.JinWeiDuFengGe;
import com.xxnx.util.qiandaoJob;
import org.apache.ibatis.session.SqlSession;
import org.quartz.JobExecutionContext;

import java.util.List;
import java.util.Objects;

class main{
    public static void main(String[] args) {
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<user> user1 = userMapper.queryusersuoyu();
        for (user stu:user1){

            user user =  userMapper.querysignById(String.valueOf(stu.getUser_id()));
            System.out.println(user.getAddress()+"+++++++++++++++++++++++");
            if (Objects.isNull(user.getLatitude())){
                user.setLatitude(String.valueOf(38.389367));
                user.setLongitude(String.valueOf(106.282515));
            }
            if (Objects.isNull(user.getAddress())){
                user.setAddress("奈雪的茶");
                user.setAddress_name("宁夏回族自治区银川金凤区万达广场115号");
            }

        }
    }
}