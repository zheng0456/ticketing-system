package com.ticking.until;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//====================== 时间计算工具类 ======================
public class TimeCalculationExample {
    
    public static String calculateTimeDifference(String startTimeStr, String endTimeStr) {
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // 将字符串转换为LocalDateTime对象
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
        
        // 计算时间差
        Duration duration = Duration.between(startTime, endTime);
        
        // 获取小时和分钟
        long hours = Math.abs(duration.toHours());
        long minutes = Math.abs(duration.toMinutes() % 60);
        
        // 格式化输出
        StringBuilder result = new StringBuilder();
        if (duration.isNegative()) {
            result.append("-");
        }
        
        result.append(hours).append("小时");
        if (minutes > 0) {
            result.append(minutes).append("分");
        }
        
        return result.toString();
    }
}