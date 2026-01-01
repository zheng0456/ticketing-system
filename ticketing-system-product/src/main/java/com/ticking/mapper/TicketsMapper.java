package com.ticking.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TicketsMapper {
    /*
     * 查询所有车票信息
     */
    @Select("select * from TICKETS where start_time >= #{departureTime} and end_time <= #{arrivalTime} and start_city LIKE concat('%', #{departure}, '%') and end_city LIKE concat('%', #{destination}, '%')" )
    List<Map<String, Object>> selectAllTickets(String destination, String departure, String departureTime,  String arrivalTime);

    /*
     * 查询所有车次 列车 信息
     */
    @Select( "select seat_type,sum(total_seats) as 'num' from train_carriage where train_id=#{train_id} Group BY seat_type")
    List<Map<String, Object>> selectTrainCarriage(Long train_id);

    /*
     * 查询车票 座位 信息
     */
    @Select("select tp.adult_price as price,tc.seat_type as seat_type,sum(tc.total_seats) as num from train_carriage tc left join train t on tc.train_id=t.id left join ticket_price tp on tp.train_id=t.id and tp.seat_type = tc.seat_type where t.id=#{ticketsId} group by tc.seat_type,tp.adult_price")
    List<Map<String, Object>> selectTicketMessages(Long ticketsId);
}
