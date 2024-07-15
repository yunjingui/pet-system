package com.three.server.service.impl;

import com.three.server.entity.Treasure;
import com.three.server.entity.UserBehavior;
import com.three.server.mapper.TreasureMapper;
import com.three.server.mapper.UserBehaviorMapper;
import com.three.server.service.TreasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreasureServiceImpl implements TreasureService {
    private final TreasureMapper treasureMapper;
    private final UserBehaviorMapper userBehaviorMapper;
    @Override
    @Transactional
    public Boolean add(Treasure treasure) {

        return treasureMapper.insert(treasure) > 0 && userBehaviorMapper.insert(
                new UserBehavior(
                        treasure.getCustomer(),
                        treasure.getGood(),
                        "收藏")
        ) > 0;
    }

    @Override
    public Boolean delete(Treasure treasure) {
        return treasureMapper.delete(treasure) > 0;
    }

    @Override
    public Boolean deleteByCustomerAndGood(Integer customer, Integer good) {
        return treasureMapper.deleteByCustomerAndGood(customer, good) > 0;
    }

    @Override
    public List<Treasure> findAllByCustomer(Integer customer) {
        return treasureMapper.selectAllByCustomer(customer);
    }

    @Override    public Integer findAllQuantityByGood(Integer good) {
        return treasureMapper.selectAllQuantityByGood(good);
    }

    public Integer findIfCollected(Integer customer, Integer good) {
        return treasureMapper.selectIfCollected(customer, good);
    }
}
