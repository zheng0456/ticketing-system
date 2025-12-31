package com.ticking.service.impl;

import com.ticking.entity.PassionEntity;
import com.ticking.mapper.PassionMapper;
import com.ticking.service.IPassionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("passionService")
public class PassionServiceImpl implements IPassionService {
    @Autowired
    PassionMapper passionMapper;

    /**
     * 添加用户乘车人
     */
    @Override
    public Boolean addPassion(PassionEntity passion) {
        boolean result=passionMapper.addPassion(passion);
        return result;
    }

    /**
     * 查询用户乘车人
     */
    @Override
    public List<PassionEntity> queryPassion(Long userId) {
        List<PassionEntity> passionList=passionMapper.queryPassion(userId);
        return passionList;
    }

    /**
     * 修改用户乘车人
     */
    @Override
    public Boolean updatePassion(PassionEntity passion) {
        boolean result=passionMapper.updatePassion(passion);
        return result;
    }
}
