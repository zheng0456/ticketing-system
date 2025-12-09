package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.entity.TrainStationEntity;
import com.ticking.service.IAdminTrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    /**
     * 修改车次站点
     */
    @PutMapping("/admin/trainStation/update/{id}")
    public @ResponseBody RestUtil updateTrainStation(@PathVariable Long id, @RequestBody Map<String,Object> trainStation) {
        Boolean result=adminTrainStationService.updateTrainStation(id,trainStation);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("修改失败");
        }
    }

    /**
     * 删除车次站点
     */
    @DeleteMapping("/admin/trainStation/delete/{id}")
    public @ResponseBody RestUtil deleteTrainStation(@PathVariable Long id) {
        Boolean result=adminTrainStationService.deleteTrainStation(id);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("删除失败");
        }
    }

    /**
     * 批量删除车次站点
     */
    @DeleteMapping("/admin/trainStation/deletes/{id}")
    public @ResponseBody RestUtil deleteTrainStation(@PathVariable Long[] id) {
        Boolean result=adminTrainStationService.deleteTrainStations(id);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("删除失败");
        }
    }
}
