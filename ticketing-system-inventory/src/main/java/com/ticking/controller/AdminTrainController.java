package com.ticking.controller;

import com.ticking.entity.RestUtil;
import com.ticking.entity.TrainEntity;
import com.ticking.service.IAdminTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return RestUtil.success(data);
    }
}
