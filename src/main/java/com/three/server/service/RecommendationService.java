package com.three.server.service;

import com.three.server.entity.Good;
import com.three.server.entity.UserBehavior;

import java.util.List;

public interface RecommendationService {
    List<Good> recommendGoods(Integer userId);
}
