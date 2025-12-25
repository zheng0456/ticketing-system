package com.ticking.until;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间格式转换工具类   将 Object 转换为 String
 */
public class TimeObject {
    public static String timeFormat(Object timeObj){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = null;
        if (timeObj instanceof LocalDateTime) {
            time = ((LocalDateTime) timeObj).format(formatter);
        } else if (timeObj instanceof String) {
            time = (String) timeObj;
        } else {
            time = timeObj != null ? timeObj.toString() : null;
        }
        return time;
    }
}
