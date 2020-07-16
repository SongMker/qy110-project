package com.aaa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.aaa.staticproperties.TimeFormatProperties.TIME_FORMAT;

 /**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/7/10 11:56
 * @Description
 **/
public class DateUtils {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        String dateString = simpleDateFormat.format(new Date());
        System.out.println(dateString);
    }

}
