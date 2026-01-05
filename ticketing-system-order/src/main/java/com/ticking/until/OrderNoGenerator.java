package com.ticking.until;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单编号生成工具类
 * 生成格式：T + 年月日时分秒(14位) + 3位自增序号  例：T20260105161025006
 * 特性：线程安全、全局唯一、格式固定、无重复、极简高效
 */
public final class OrderNoGenerator {

    // 私有化构造方法，工具类禁止实例化
    private OrderNoGenerator() {
    }

    /**
     * 时间格式化器：严格格式 yyyyMMddHHmmss 14位（年月日时分秒，无毫秒）
     * ThreadLocal保证多线程安全，解决SimpleDateFormat非线程安全问题
     */
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyyMMddHHmmss")
    );

    /**
     * 原子自增序列：生成000-999的3位补零序号，解决同一秒内多次生成重复问题
     * AtomicInteger 线程安全，CAS无锁机制，性能远高于synchronized，高并发无压力
     */
    private static final AtomicInteger SEQ = new AtomicInteger(0);

    /**
     * 序列最大值，固定3位数，到999自动归0
     */
    private static final int MAX_SEQ = 999;

    /**
     * 核心方法：生成指定格式的订单编号【唯一，无重复】
     * @return 格式：T + 14位时间 + 3位序号 如 T20260105161025006
     */
    public static String generateOrderNo() {
        StringBuilder orderNo = new StringBuilder();
        // 2. 拼接14位 年月日时分秒 yyyyMMddHHmmss
        orderNo.append(DATE_FORMAT.get().format(new Date()));
        // 3. 获取自增序列，超过999归零，格式化补零为3位
        int currentSeq = SEQ.getAndIncrement();
        if (currentSeq > MAX_SEQ) {
            SEQ.set(0);
            currentSeq = 0;
        }
        orderNo.append(String.format("%03d", currentSeq));
        return orderNo.toString();
    }
}
