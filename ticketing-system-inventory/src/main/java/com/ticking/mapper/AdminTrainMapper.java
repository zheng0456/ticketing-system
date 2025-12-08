package com.ticking.mapper;

import com.ticking.entity.TrainCarriageEntity;
import com.ticking.entity.TrainEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminTrainMapper {
    @Select({"<script>",
            "SELECT * FROM train where operate_status = 1 or operate_status = 2",
            "</script>"})
    List<TrainEntity> selectAllTrain();

    @Select({"<script>",
            "SELECT * FROM train_carriage where train_id = #{id}",
            "</script>"})
    List<TrainCarriageEntity> selectAllCarriages(Long id);
}
