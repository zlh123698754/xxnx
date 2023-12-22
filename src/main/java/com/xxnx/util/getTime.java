package com.xxnx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class getTime {
    public List<String> YuegatStarttime() throws ParseException {
        List<String> strings = new ArrayList<>();
        //指定日期字符串--转换为--日期格式Calendar
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cld = Calendar.getInstance();
        cld.setTime(new Date());

//月份+1，天设置为0。下个月第0天，就是这个月最后一天
        cld.add(Calendar.MONTH, 1);
        cld.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = sdf.format(cld.getTime());
        System.out.println("本月最后一天："+lastDay);

        cld.set(Calendar.DAY_OF_MONTH,1);
        System.out.println("本月第一天："+ sdf.format(cld.getTime()) );

        strings.add(lastDay);
        strings.add(sdf.format(cld.getTime()));

        return strings;
    }

    public List<String> ZhougatStarttime() throws ParseException {
        List<String> strings = new ArrayList<>();
        //指定日期字符串--转换为--日期格式Calendar
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setTime(new Date());


        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //周一
        System.out.println(sdf.format(cld.getTime()));

        strings.add(sdf.format(cld.getTime()));
        cld.add(Calendar.DATE, 7);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //周日
        System.out.println(sdf.format(cld.getTime()));


        strings.add(sdf.format(cld.getTime()));

        return strings;
    }
}
