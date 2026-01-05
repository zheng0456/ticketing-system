package com.ticking.until;

import java.util.HashMap;
import java.util.Map;

public class SelectedSeats {
    /**
     * 解析选座信息字符串，格式如："一等座:A | 商务座:A"
     * @param selectedSeats 选座信息字符串
     * @return 包含座位类型和对应座位号的Map，key为座位类型，value为座位号
     */
    public static Map<String, String> parseSelectedSeats(String selectedSeats) {
        Map<String, String> seatInfoMap = new HashMap<>();

        if (selectedSeats == null || selectedSeats.trim().isEmpty()) {
            return seatInfoMap;
        }

        // 按 " | " 分割不同的座位类型
        String[] seatPairs = selectedSeats.split(" \\\\| ");

        for (String seatPair : seatPairs) {
            // 按 ":" 分割座位类型和座位号
            String[] parts = seatPair.split(":");
            if (parts.length == 2) {
                String seatType = parts[0].trim();  // 座位类型，如"一等座"
                String seatNumber = parts[1].trim(); // 座位号，如"A"
                seatInfoMap.put(seatType, seatNumber);
            }
        }

        return seatInfoMap;
    }
}