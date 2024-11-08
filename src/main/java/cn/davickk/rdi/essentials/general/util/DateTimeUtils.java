package cn.davickk.rdi.essentials.general.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static Timestamp getTimestampNow(){
        java.util.Date date = new Date(System.currentTimeMillis());
        Timestamp param = new java.sql.Timestamp(date.getTime());
        return param;
    }
    public static String getFormattedDateTime(LocalDateTime dateTime){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy年MM月dd日 E HH:mm:ss");
        String formattedDateTime = dateTime.format(myFormatObj).replace("Mon", "周一")
                .replace("Tue", "周二").replace("Wed", "周三")
                .replace("Thu", "周四").replace("Fri", "周五")
                .replace("Sat", "周六").replace("Sun", "周日");
        return formattedDateTime;
    }
    public static String getFormattedDateTime(LocalDateTime dateTime,String pattern){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = dateTime.format(myFormatObj).replace("Mon", "周一")
                .replace("Tue", "周二").replace("Wed", "周三")
                .replace("Thu", "周四").replace("Fri", "周五")
                .replace("Sat", "周六").replace("Sun", "周日");
        return formattedDateTime;
    }
    public static String getWeekByInt(int day){
        switch (day){
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
            default:
                return "?";
        }
    }
    /**
     * 1.day相同 - 今天
     * 2.day差1  - 昨天
     * 3.day差2~7 - 周123/上周456日
     *
     *
     * @param dtRec 旧日期
     * @param dtNow 新日期--现在的日期
     * @return 类似于 今天9：20，昨天2：40，周二2：49，上周六22：45，4月4日12：45，20xx年x月x日 xx:xx
     */
    public static String getComparedDateTime(LocalDateTime dtRec,LocalDateTime dtNow){
        String formattedTimePrefix = null;
        //如果不是今年，返回完整记录
        if(!(dtRec.getYear()==dtNow.getYear())){
            return getFormattedDateTime(dtRec);
        }
        //如果是今年，但 不是这个月，返回X月X日 xx:xx记录
        if(!(dtRec.getMonth().getValue()==dtNow.getMonth().getValue())){
            return getFormattedDateTime(dtRec,"MM月dd日HH:mm");
        }

        //如果是今年，是 这个月，但是日期是 前天 或更早（），显示周几/上周几
        if(dtNow.getDayOfYear() - dtRec.getDayOfYear()>=2){
            int dt2Week=dtNow.getDayOfWeek().getValue();
            int dt1Week=dtRec.getDayOfWeek().getValue();
            //早周X>=晚周X说明是上周
            if(dt1Week>=dt2Week)
                formattedTimePrefix="上周"+getWeekByInt(dt1Week);
            else
                formattedTimePrefix="周"+getWeekByInt(dt2Week);
        }
        //如果是昨天
        if(dtRec.getDayOfYear()==dtNow.getDayOfYear()-1){
            formattedTimePrefix="昨天";
        }
            //如果是今天
        if(dtRec.getDayOfYear()==dtNow.getDayOfYear()){
            formattedTimePrefix="今天";
        }



        return formattedTimePrefix+dtRec.getHour()+":"+dtRec.getMinute();

    }
}
