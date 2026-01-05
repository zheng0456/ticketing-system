package com.ticking.mapper;

import com.ticking.entity.SeatMessageEntity;
import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CreateOrderMapper {
    /**
     * 根据用户ID获取用户信息
     */
    @Select("SELECT * FROM user WHERE user_id = #{userId} and status=1")
    UserEntity selectById(Long userId);

    /**
     * 根据车次ID获取座位信息
     */
    @Select("select * from SEAT_MESSAGES where id=#{trainId}")
    List<SeatMessageEntity> selectSeats(Long trainId);

    void createOrder(Long orderId, Long userId, Long trainId, String ticketType, BigDecimal price, String idNumber);
}
