package cn.davickk.rdi.essentials.general.util;

import java.sql.Date;
import java.sql.Timestamp;

public class TimeUtils {
    public static Timestamp getTimestampNow(){
        java.util.Date date = new Date(System.currentTimeMillis());
        Timestamp param = new java.sql.Timestamp(date.getTime());
        return param;
    }
}
