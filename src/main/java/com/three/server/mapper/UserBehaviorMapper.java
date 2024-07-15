package com.three.server.mapper;

import com.three.server.entity.UserBehavior;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBehaviorMapper {
    List<UserBehavior> selectByUserId(Integer userId);
    List<UserBehavior> selectByGoodId(Integer goodId);
    List<Integer> selectDistinctGoodIdsByUserId(Integer userId); // 获取用户行为中的所有商品id
    List<UserBehavior> selectAll();

    int insert(UserBehavior userBehavior);
    int deleteByUserIdAndGoodId(Integer userId, Integer goodId);
}
