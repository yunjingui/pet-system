package com.three.server.mapper;

import com.three.server.entity.Good;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodMapper {
    int insert(Good good);
    int deleteById(Integer id);
    int update(Good good);
    int updateGoodLevel(Integer id);
    List<Good> selectAll();
    Good selectById(Integer id);
    List<Good> selectByName(String name);
    List<Good> selectBySellerId(Integer sellerId);
    List<Good> selectBySecondClassification(Integer secondClassification);
    List<Good> selectByNameAndClassificationAndLowestAndHighest(@Param("name") String name, @Param("classification") Integer classification, @Param("lowest") Integer lowest, @Param("highest") Integer highest);
    Double rankForGood(Integer id);

    int plusGoodSale(Integer id);
    int plusGoodFlow(Integer id);

    Double isDiscount(Integer id);
    int uploadAvatar(Integer id, String avatar);

    int insertGoodImage(Integer id, String imageUrl);
    int deleteGoodImage(Integer id);
    String selectGoodImage(Integer id);
    List<String> selectGoodImageByGoodId(Integer id);


    List<Good> selectByGoodIds(List<Integer> goodIds);

}
