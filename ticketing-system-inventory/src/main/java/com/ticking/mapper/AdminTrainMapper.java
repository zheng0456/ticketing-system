package com.ticking.mapper;

import com.ticking.entity.TrainCarriageEntity;
import com.ticking.entity.TrainEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AdminTrainMapper {
    /**
     * 查询车次信息
     */
    @Select("SELECT * FROM train")
    List<TrainEntity> selectAllTrain();

    /**
     * 添加车次信息
     */
    @Insert("insert into train(id,train_no,start_station_id,end_station_id,start_time,end_time,run_duration,train_type,operate_status,create_time,maintenance_time,note,fuyi_time) values(#{id},#{trainNumber},#{startStation},#{endStation},#{departureTime},#{arrivalTime},#{runDuration},#{trainType},#{status},#{manufactureDate},#{lastMaintenanceDate},#{remark},#{serviceLife})")
    int addTrain(Long id,String departureTime, String arrivalTime, Integer startStation, Integer endStation, String trainType, String trainNumber, Integer status, Double serviceLife, String remark, String manufactureDate, String lastMaintenanceDate, String runDuration);

    /**
     * 添加车厢信息
     */
    @Insert("insert into train_carriage(id,train_id,carriage_no,seat_type,total_seats) values(#{trainId},#{id},#{rangeInt},#{name},#{i})")
    int addTrainCarriage(Long trainId,Long id, int rangeInt, String name, int i);

    /**
     * 添加座位信息
     */
    @Insert("insert into train_seat(id,carriage_id,seat_no,seat_status) values(#{seatId},#{id},#{i},0)")
    int addTrainSeat(Long seatId,Long id, String i);

    /**
     * 查询车次始发站名称信息 和 终点站名称信息
     */
    @Select("SELECT station_name FROM train_station WHERE id=#{id}")
    String selectTrainStationByTrainId(Long startStationId);

    /**
     * 修改车次信息
     */
    @Update("update train set start_station_id=#{startStation},end_station_id=#{endStation},start_time=#{departureTime},end_time=#{arrivalTime},operate_status=#{status},maintenance_time=#{lastMaintenanceDate},note=#{remark},fuyi_time=#{valueOf} where id=#{id}")
    Boolean updateTrain(Long id, String departureTime, String arrivalTime, Integer startStation, Integer endStation, Integer status, Double valueOf, String remark, String lastMaintenanceDate);

    /**
     * 删除车次信息
     */
    @Delete("delete from train where id=#{id}")
    Boolean deleteTrain(Long id);

    /**
     * 删除车厢信息
     */
    @Delete("delete from train_carriage where train_id= #{id}")
    Boolean deleteTrainCarriages(Long id);

    /**
     * 查询座位信息
     */
    @Select("SELECT id FROM train_carriage WHERE train_id= #{id}")
    List<Long> selectTrainCarriagesID(Long id);

    /**
     * 删除座位信息
     */
    @Delete("delete from train_seat where carriage_id= #{carriageID}")
    Boolean deleteTrainSeats(Long carriageID);

    /**
     * 查询车次编号
     */
    @Select("SELECT train_no FROM train WHERE id= #{trainId}")
    String selectTrainNumber(Long trainId);

    /**
     * 查询车次信息 根据 火车id 查询车次信息
     */
    @Select("SELECT * FROM train WHERE id= #{trainId}")
    TrainEntity selectTrain(Long trainId);

    /**
     * 查询车厢信息 根据 火车id 获取车厢信息
     */
    @Select("SELECT DISTINCT * FROM train_carriage WHERE train_id= #{trainId}")
    List<TrainCarriageEntity> selectTrainCarriage(Long trainId);

    /**
     * 删除车票信息
     */
    @Delete("delete from ticket_price where train_id= #{id}")
    Boolean deleteTrainTickets(Long id);
}
