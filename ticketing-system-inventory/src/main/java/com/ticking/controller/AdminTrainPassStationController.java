package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.entity.TrainPassStationEntity;
import com.ticking.service.IAdminTrainPassStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AdminTrainPassStationController {
    @Autowired
    IAdminTrainPassStationService adminTrainPassStationService;
    /**
     * 查询途径车站站点
     */
    @PostMapping("/admin/trainPassStation")
    public @ResponseBody RestUtil getTrainPassStation() {
        List<TrainPassStationEntity> data=adminTrainPassStationService.selectAllTrainPassStation();
        return RestUtil.success(data);
    }
    /**
     * 添加途径车站站点
     */
    @PostMapping("/admin/trainPassStation/add")
    public @ResponseBody RestUtil addTrainPassStation(@RequestBody Map<String,Object> trainPassStation) {
        Boolean result= adminTrainPassStationService.addTrainPassStation(trainPassStation);
        if (result==false){
            return RestUtil.error("添加失败");
        }
        return RestUtil.success();
    }
    /**
     * 修改途径车站站点
     */
    @PostMapping("/admin/trainPassStation/update")
    public @ResponseBody RestUtil updateTrainPassStation(@RequestBody Map<String,Object> trainPassStation) {
        Boolean result= adminTrainPassStationService.updateTrainPassStation(trainPassStation);
        if (result==false){
            return RestUtil.error("修改失败");
        }
        return RestUtil.success();
    }
    /**
     * 删除途径车站站点
     */
    @PostMapping("/admin/trainPassStation/delete")
    public @ResponseBody RestUtil deleteTrainPassStation(@RequestBody Long [] id) {
        Boolean result=false;
        for (Long i:id){
            result=adminTrainPassStationService.deleteTrainPassStation(i);
        }
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("删除失败");
        }
    }
}
