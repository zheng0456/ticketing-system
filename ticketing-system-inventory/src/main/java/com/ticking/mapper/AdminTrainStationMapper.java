package com.ticking.mapper;

import com.ticking.entity.TrainStationEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminTrainStationMapper {

    @Select("select * from train_station")
    List<TrainStationEntity> selectAllTrainStation();

    @Insert("insert into train_station(station_name,province,city,status,sort_no,address,vote,create_time) values(#{stationName},#{province},#{city},#{status},#{sortNo},#{address},#{vote},#{createTime})")
    Boolean addTrainStation(TrainStationEntity trainStation);

    @Update("update train_station set station_name=#{stationName},province=#{province},city=#{city},status=#{status},address=#{address},vote=#{vote},create_time=#{createTime} where id=#{id}")
    Boolean updateTrainStation(Long id,String stationName,String province,String city,Integer status,String address,String vote,String createTime);

    @Delete("delete from train_station where id=#{id}")
    Boolean deleteTrainStation(Long id);
}
