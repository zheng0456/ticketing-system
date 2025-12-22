package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.entity.TrainEntity;
import com.ticking.service.IAdminTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 管理员 管理火车实体类
 */
@Controller
public class AdminTrainController {
    @Autowired
    IAdminTrainService adminTrainService;
    /**
     * 展示 车辆详情
     */
    @PostMapping("/admin/train")
    public @ResponseBody RestUtil queryTrain() {
        List<TrainEntity> data=adminTrainService.selectTrainMessages();
        if (data==null){
            return RestUtil.error("暂无数据");
        }
        return RestUtil.success(data);
    }

    /**
     * 添加车辆
     */
    @PostMapping("/admin/train/add")
    public @ResponseBody RestUtil addTrain(@RequestBody Map<String,Object>  train) {
        Boolean result=adminTrainService.addTrain(train);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("添加失败");
        }
    }
    /**
     * 修改车辆
     */
    @PutMapping("/admin/train/update/{id}")
    public @ResponseBody RestUtil updateTrain(@PathVariable Long id, @RequestBody Map<String,Object> train) {
        Boolean result=adminTrainService.updateTrain(id,train);
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("修改失败");
        }
    }
    /**
     * 删除车辆
     */
    @PostMapping("/admin/train/deletes")
    public @ResponseBody RestUtil deleteTrain(@RequestBody Long [] id) {
        Boolean result=false;
        for (Long i:id){
            result=adminTrainService.deleteTrain(i);
        }
        if (result==true){
            return RestUtil.success();
        }else {
            return RestUtil.error("删除失败");
        }
    }
}
