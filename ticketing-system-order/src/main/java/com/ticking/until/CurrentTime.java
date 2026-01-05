package com.ticking.until;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
    /**
     * 获取当前时间
     * @return
     */
    public static String createCurrentTime(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createTime = currentTime.format(formatter);
        return createTime;
    }
    /**
     * 获取当前时间加30分钟
     * @return
     */
    public static String afterTime(){
        LocalDateTime currentTime = LocalDateTime.now().plusMinutes(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createTime = currentTime.format(formatter);
        return createTime;
    }
}
