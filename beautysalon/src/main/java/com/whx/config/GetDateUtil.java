package com.whx.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateUtil {
    /**
     * 获取以当前时间的id
     * @return
     */
    public static Long  getCurrentTimeForId(){

        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        return Long.parseLong(format);

    }
}
