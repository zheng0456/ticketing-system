package com.ticking.mapper;

import com.ticking.entity.SeatMessageEntity;
import com.ticking.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CreateOrderMapper {
    /**
     * 根据车次ID获取座位信息
     */
    @Select("select * from SEAT_MESSAGES where id=#{trainId} and seat_type=#{seatType} and seat_no like #{seatNo}")
    List<SeatMessageEntity> selectSeats(Long trainId,String seatType,String seatNo);

    @Insert("insert into order_info(id,order_no,user_id,train_id,start_station_id,end_station_id,carriage_id,seat_id,seat_no,ticket_type,ticket_price,order_status,user_card_id,create_time,pay_deadline) values(#{orderId},#{orderNo},#{userId},#{trainId},#{startStationId},#{endStationId},#{carriageId},#{seatId},#{seatNo},#{ticketType},#{price},0,#{idNumber},#{createTime},#{payTime})")
    Boolean createOrder(Long orderId,String orderNo, Long userId, Long trainId,Long startStationId,Long endStationId,String ticketType, BigDecimal price, String idNumber, Long carriageId, Long seatId, String seatNo,String createTime,String payTime);
}
