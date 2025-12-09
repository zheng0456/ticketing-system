package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.entity.TrainStationEntity;
import com.ticking.service.IAdminTrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminTrainStationController {

    @Autowired
    IAdminTrainStationService adminTrainStationService;
    /**
     * 查看所有车次站点
     */
    @PostMapping("/admin/trainStation")
    public @ResponseBody RestUtil queryTrainStation() {
        List<TrainStationEntity> data=adminTrainStationService.selectAllTrainStation();
        return RestUtil.success(data);
    }

    /**
     * 添加车次站点
     */
    @PostMapping("/admin/trainStation/add")
    public @ResponseBody RestUtil addTrainStation(@RequestBody TrainStationEntity trainStation) {
        Boolean result=adminTrainStationService.addTrainStation(trainStation);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("添加失败");
        }
    }
}
