package com.ticking.utility;

/**
 * 封装Long对象
 *
 * 将 Object 数组 转换成 Long 数组对象
 */
public class LongObject {
    public static Long[] toLong(Object [] obj) {
        // 类型安全转换：将Object数组转换为Long数组
        Long[] longIds = new Long[obj.length];
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] instanceof Integer) {
                longIds[i] = ((Integer) obj[i]).longValue();
            } else if (obj[i] instanceof Long) {
                longIds[i] = (Long) obj[i];
            } else {
                longIds[i] = Long.valueOf(obj[i].toString());
            }
        }
        return longIds;
    }
}
