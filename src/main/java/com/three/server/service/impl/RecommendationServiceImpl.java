package com.three.server.service.impl;

import com.three.server.entity.Good;
import com.three.server.entity.UserBehavior;
import com.three.server.mapper.GoodMapper;
import com.three.server.mapper.UserBehaviorMapper;
import com.three.server.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final UserBehaviorMapper userBehaviorMapper;
    private final GoodMapper goodMapper;
    @Override
    public List<Good> recommendGoods(Integer userId) {
        List<Integer> userGoodIds = userBehaviorMapper.selectDistinctGoodIdsByUserId(userId);
        if (userGoodIds.isEmpty()) {
            return Collections.emptyList();
        }
        // 获取所有用户的商品偏好
        List<UserBehavior> allBehaviors = userBehaviorMapper.selectAll();

        // 基于用户的协同过滤算法
        Map<Integer, Integer> goodPreferences = new HashMap<>();
        for (UserBehavior behavior : allBehaviors) {
            if (!userGoodIds.contains(behavior.getGoodId())) {
                goodPreferences.put(behavior.getGoodId(), goodPreferences.getOrDefault(behavior.getGoodId(), 0) + 1);
            }
        }

        // 根据偏好度排序并返回前10个推荐商品
        List<Map.Entry<Integer, Integer>> sortedPreferences = goodPreferences.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());

        List<Integer> recommendedGoodIds = sortedPreferences.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return goodMapper.selectByGoodIds(recommendedGoodIds);
    }
}
