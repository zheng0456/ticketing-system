package com.ticking.mapper;

import com.ticking.entity.TrainPassStationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
