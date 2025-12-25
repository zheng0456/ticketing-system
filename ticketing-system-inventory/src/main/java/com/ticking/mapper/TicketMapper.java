package com.ticking.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    /**
     * 查询所有车票信息
     */
    @Select("select * from TICKET_MESSAGES")
    List<Map<String, Object>> selectAllTickets();

    /**
     * 添加车票信息
     */
    @Insert("insert into ticket_price (train_id,start_station_id,end_station_id,seat_type) values(#{id},#{startStation},#{endStation},#{seatType})")
    void addTickets(long id,String seatType,Integer startStation,Integer endStation);

    /**
     * 修改车票信息
     */
    @Insert("update ticket_price set adult_price=#{price},child_price=#{childPrice},student_price=#{studentPrice} where id=#{id}")
    Boolean updateTicket(Long id, BigDecimal price,BigDecimal childPrice,BigDecimal studentPrice);
}
