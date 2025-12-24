package com.ticking.mapper;

import com.ticking.entity.TrainPassStationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminTrainPassStationMapper {
    /**
     * 添加途径站点
     */
    @Insert("insert into train_pass_station(train_id,station_id,pass_order,depart_time,arrive_time,stay_duration) values(#{trainId},#{stationId},#{passStationOrder},#{departureTime},#{arrivalTime},#{time})")
    Boolean addTrainPassStation(Long trainId, Long stationId, String passStationOrder, String departureTime, String arrivalTime, String time);
    /**
     * 查询所有途径站点
     */
    @Select("select * from train_pass_station")
    List<TrainPassStationEntity> selectAllTrainPassStation();
    /**
     * 修改途径站点
     */
    @Update("update train_pass_station set train_id=#{valueOf},station_id=#{valueOf1},pass_order=#{passStationOrder},depart_time=#{departureTime},arrive_time=#{arrivalTime},stay_duration=#{time} where id=#{id}")
    Boolean updateTrainPassStation(Long valueOf, Long valueOf1, String passStationOrder, String departureTime, String arrivalTime, String time,Long  id);
}
