package net.ebzh.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String  getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());
    }

    public static String fomatDate(String date){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return date.substring(0,10);
    }

    public static void main(String[] args){
        getNowTime();
    }
}
