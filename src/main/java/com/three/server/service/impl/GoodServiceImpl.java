package com.three.server.service.impl;

import com.three.server.entity.Good;
import com.three.server.mapper.GoodMapper;
import com.three.server.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    private final GoodMapper goodMapper;

    @Override
    public Integer addGood(Good good) {
        goodMapper.insert(good);
        return good.getId() != null ? good.getId() : null;
    }

    @Override
    public Boolean deleteGoodById(Integer id) {
        return goodMapper.deleteById(id) > 0;
    }

    @Override
    public Boolean updateGood(Good good) {
        return goodMapper.update(good) > 0;
    }

    @Override
    public List<Good> findAllGoods() {
        return goodMapper.selectAll();
    }

    @Override
    public Good findGoodById(Integer id) {
        return goodMapper.selectById(id);
    }

    @Override
    public List<Good> findGoodsByName(String name) {
        return goodMapper.selectByName(name);
    }

    @Override
    public List<Good> findGoodsBySellerId(Integer sellerId) {
        return goodMapper.selectBySellerId(sellerId);
    }

    @Override
    public List<Good> findGoodsBySecondClassification(Integer secondClassification) {
        return goodMapper.selectBySecondClassification(secondClassification);
    }


    @Override
    public List<Good> findGoodsByNameAndClassificationAndLowQuantityAndHighQuantity(String name, Integer classification, Integer lowest, Integer highest) {
        return goodMapper.selectByNameAndClassificationAndLowestAndHighest(name, classification, lowest, highest);
    }

    @Override
    public Double rankForGood(Integer id) {
        return goodMapper.rankForGood(id);
    }

    @Override
    public Boolean plusGoodSale(Integer id) {
        return goodMapper.plusGoodSale(id) > 0;
    }

    @Override
    public Boolean plusGoodFlow(Integer id) {
        return goodMapper.plusGoodFlow(id) > 0;
    }

    @Override
    public Double isDiscount(Integer id) {
        return goodMapper.isDiscount(id);
    }

    @Override
    public Boolean uploadAvatar(Integer id, String avatar) {
        return goodMapper.uploadAvatar(id, avatar) > 0;
    }

    @Override
    public Boolean addGoodImage(Integer id, String imageUrl) {
        return goodMapper.insertGoodImage(id, imageUrl) > 0;
    }

    @Override
    public Boolean deleteGoodImage(Integer id) {
        return goodMapper.deleteGoodImage(id) > 0;
    }

    @Override
    public String goodImageDisplay(Integer id) {
        return goodMapper.selectGoodImage(id);
    }

    @Override
    public List<String> goodImageDisplayByGoodId(Integer id) {
        return goodMapper.selectGoodImageByGoodId(id);
    }
}
