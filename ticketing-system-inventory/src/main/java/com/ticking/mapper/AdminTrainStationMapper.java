package com.ticking.mapper;

import com.ticking.entity.TrainStationEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminTrainStationMapper {

    @Select("select * from train_station")
    List<TrainStationEntity> selectAllTrainStation();

    @Insert("insert into train_station(station_name,province,city,status,sort_no,address,vote,create_time) values(#{stationName},#{province},#{city},#{status},#{sortNo},#{address},#{vote},#{createTime})")
    Boolean addTrainStation(TrainStationEntity trainStation);
}
