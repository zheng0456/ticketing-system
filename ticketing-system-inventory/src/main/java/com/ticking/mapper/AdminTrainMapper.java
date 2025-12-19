package com.ticking.mapper;

import com.ticking.entity.TrainCarriageEntity;
import com.ticking.entity.TrainEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AdminTrainMapper {
    /**
     * 查询车次信息
     */
    @Select("SELECT * FROM train where operate_status = 1 or operate_status = 2 or operate_status = 0")
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
     * 添加座位信息  普通列车 和 动车
     */
    @Insert("insert into train_seat(carriage_id,seat_no,seat_status) values(#{id},#{i},0)")
    int addTrainSeat(Long id, String i);
}
