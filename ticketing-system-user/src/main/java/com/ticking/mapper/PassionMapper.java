package com.ticking.mapper;

import com.ticking.entity.PassionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PassionMapper {
    /**
     * 添加用户乘车人
     */
    @Insert("insert into user_id_card(user_id,name,phone,card_id,card_type,discount_type) values(#{userId},#{name},#{phone},#{cardId},#{cardType},#{discountType})")
    boolean addPassion(PassionEntity passion);

    /**
     * 查询用户乘车人
     */
    @Select("select * from user_id_card where user_id=#{userId}")
    List<PassionEntity> queryPassion(Long userId);

    /**
     * 修改用户乘车人
     */
    @Update("update user_id_card set name=#{name},phone=#{phone},card_id=#{cardId},card_type=#{cardType},discount_type=#{discountType} where id=#{id}")
    boolean updatePassion(PassionEntity passion);
}
